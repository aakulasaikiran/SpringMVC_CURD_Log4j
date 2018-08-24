package net.codejava.spring.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;

import net.codejava.spring.model.Employee;

/**
 * An implementation of the EmployeeDAO interface.
 * 
 * @author saikiran
 *
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryExt {
	Logger logger = LoggerFactory.getLogger("HomeController");

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Employee> findAll() {
		logger.info("In Side list method() of EmployeeDAOImpl");
		return mongoTemplate.findAll(Employee.class, "Employee");
	}

	public void delete(String email) {
		logger.info("In Side delete method() of EmployeeDAOImpl");
		Query query = new Query(Criteria.where("email").is(email));
		logger.debug("query " + query);
		mongoTemplate.remove(query, Employee.class);
	}

	public List<Employee> sortbysalary() {
		Query query = new Query().with(new Sort("salary", "1"));

		return mongoTemplate.find(query, Employee.class);
	}

	public Employee getedit(String email, Employee employee) {
		logger.info("In Side getedit method() of EmployeeDAOImpl");

		Query query = new Query(Criteria.where("email").is(email));
		logger.debug("query " + query);

		return mongoTemplate.findOne(query, Employee.class);
	}

	public void EUpdate(Employee employee1) {
		logger.info("In Side Update method() of EmployeeDAOImpl");
		String email = employee1.getEmail();
		logger.debug("email " + email);

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		logger.debug("query " + query);
		Update update = new Update();
		update.set("name", employee1.getName());
		update.set(email, employee1.getEmail());
		update.set("address", employee1.getAddress());
		update.set("telephone", employee1.getTelephone());
		update.set("salary", employee1.getSalary());
		update.set("deportment", employee1.getDeportment());
		logger.debug("update " + update);

		mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().upsert(true), Employee.class, "Employee");

	}

	public Set<String> getDepartments() {
		ArrayList<BasicDBObject> pipeline = new ArrayList<BasicDBObject>();
		BasicDBObject project = new BasicDBObject("$project", new BasicDBObject("deportment", "$deportment"));
		BasicDBObject groupParam = new BasicDBObject("_id", "$deportment").append("deportment",
				new BasicDBObject("$distinct", "$deportment"));
		pipeline.add(project);

		BasicDBObject cmdBody = new BasicDBObject("aggregate", "Employee");
		cmdBody.put("pipeline", pipeline);
		BasicDBObject cursor = new BasicDBObject("batchSize", 10000);
		cmdBody.put("cursor", cursor);
		CommandResult commandResult = mongoTemplate.executeCommand(cmdBody);

		DBObject db = (DBObject) commandResult.get("cursor");
		Iterable<DBObject> s = (Iterable<DBObject>) db.get("firstBatch");
		Set<String> li = new HashSet();

		for (DBObject y : s) {
			if (y.containsField("deportment")) {
				String s1 = (String) y.get("deportment");
				li.add(s1);

				System.out.println(li);

			}

		}
		return li;
	}

	public Employee aggregate(String employeeDepartment) {
		ArrayList<BasicDBObject> pipeline = new ArrayList<BasicDBObject>();

		BasicDBObject matchParam = new BasicDBObject("$match", new BasicDBObject("deportment", employeeDepartment));

		BasicDBObject projectparam = new BasicDBObject("$project",
				new BasicDBObject("deportment", "$deportment").append("salary", "$total"));

		// group param
		BasicDBObject groupParam = new BasicDBObject("$group",
				new BasicDBObject("_id", "$deportment").append("total", new BasicDBObject("$sum", "$salary")));
		pipeline.add(matchParam);
		pipeline.add(groupParam);
		pipeline.add(projectparam);
		BasicDBObject cmdBody = new BasicDBObject("aggregate", "Employee");
		cmdBody.put("pipeline", pipeline);
		BasicDBObject cursor = new BasicDBObject("batchSize", 10000);
		cmdBody.put("cursor", cursor);
		CommandResult commandResult = mongoTemplate.executeCommand(cmdBody);
		
		DBObject db = (DBObject) commandResult.get("cursor");
		Iterable<DBObject> s = (Iterable<DBObject>) db.get("firstBatch");
		
		int salary = 0;
		String dept = null;
		for (DBObject y : s) {

			dept = (String) y.get("_id");

			try {
				salary = Integer.valueOf(y.get("salary").toString());
				System.out.println(salary);
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		Employee contact = new Employee();
		contact.setDeportment(dept);
		contact.setSalary(salary);

		return contact;

	}
	public List<Employee> groupByDepList1(String groupname) {
		Query query = new Query();

		query.addCriteria(Criteria.where("deportment").is(groupname));
		return mongoTemplate.find(query, Employee.class);

	}
}
