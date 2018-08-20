package net.codejava.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.Employee;

import org.bson.BasicBSONDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.mongodb.BasicDBObject;

/**
 * An implementation of the EmployeeDAO interface.
 * 
 * @author saikiran
 *
 */
public class EmployeeDAOImpl implements EmployeeDAO {
	Logger logger = LoggerFactory.getLogger("HomeController");
	// @Autowired()
	private MongoTemplate mongoTemplate;

	public EmployeeDAOImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void saveOrUpdate(Employee employee) {
		logger.info("In Side saveOrUpdate method() of EmployeeDAOImpl");
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(employee.getEmail()));
		Update update = new Update();
		update.set("name", employee.getName());
		update.set("email", employee.getEmail());
		update.set("address", employee.getAddress());
		update.set("telephone", employee.getTelephone());
		update.set("salary", employee.getSalary());
		update.set("deportment", employee.getDeportment());
		/*
		 * mongoTemplate.findAndModify(query, update, Employee.class);
		 */
		//mongoTemplate.save(employee);
		mongoTemplate.upsert(query, update, Employee.class);
		/*
		 * else { int contactId = contact.getId(); Query query = new
		 * Query(Criteria.where("id").is(contactId));
		 * 
		 * 
		 * mongoTemplate.save(contact); }
		 */
		/*
		 * if (contact.getId() > 0) {
		 * 
		 * BasicDBObject query = new BasicDBObject("id", contact.getId());
		 * 
		 * Query query1 = new Query(Criteria.where("id").is(contact.getId()));
		 * 
		 * mongoTemplate.updateFirst(query1, Update.update("name",
		 * contact.getName()), Contact.class);
		 * 
		 * } else { Query query1 = new
		 * Query(Criteria.where("id").is(contact.getId()));
		 * mongoTemplate.updateFirst(query1, Update.update("name",
		 * contact.getName()), Contact.class);
		 * 
		 * 
		 * 
		 * mongoTemplate.save(contact); }
		 */

	}

	public void delete(String email) {
		logger.info("In Side delete method() of EmployeeDAOImpl");
		Query query = new Query(Criteria.where("email").is(email));
		logger.debug("query " + query);
		mongoTemplate.remove(query, Employee.class);
	}

	public List<Employee> list() {
		logger.info("In Side list method() of EmployeeDAOImpl");
		return mongoTemplate.findAll(Employee.class, "Employee");
	}

	public Employee get(String email, Employee employee) {
		logger.info("In Side get method() of EmployeeDAOImpl");
		Query query = new Query(Criteria.where("email").is(email));
		logger.debug("query " + query);
		Update update = new Update();
		// update.set("id",contact.getId());
		update.set("name", employee.getName());
		update.set("email", employee.getEmail());
		update.set("address", employee.getAddress());
		update.set("telephone", employee.getTelephone());
		logger.debug("update " + update);
		return mongoTemplate.findAndModify(query, update, Employee.class);

	}

	public Employee getedit(String email, Employee employee) {
		logger.info("In Side getedit method() of EmployeeDAOImpl");

		Query query = new Query(Criteria.where("email").is(email));
		logger.debug("query " + query);
		/*
		 * Update update = new Update(); // update.set("id",contact.getId());
		 * update.set("name", employee.getName()); update.set("email",
		 * employee.getEmail()); update.set("address", employee.getAddress());
		 * update.set("telephone", employee.getTelephone()); return
		 * mongoTemplate.findAndModify(query, update, Employee.class);
		 */

		return mongoTemplate.findOne(query, Employee.class);
	}

	public void Update(Employee employee1) {
		logger.info("In Side Update method() of EmployeeDAOImpl");
		String email = employee1.getEmail();
		logger.debug("email " + email);
		/*
		 * mongoTemplate.remove(employee1); mongoTemplate.save(employee1);
		 */
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
		// mongoTemplate.updateMulti(query, update, Employee.class);
		mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().upsert(true), Employee.class, "Employee");

	}

	/*
	 * public List<Employee> groupByDep(String groupname) { Query query = new
	 * Query();
	 * 
	 * query.addCriteria(Criteria.where("deportment").is(groupname)); return
	 * mongoTemplate.find(query, Employee.class);
	 * 
	 * }
	 */

	public List<Employee> groupByDep(String groupname) {
		Query query = new Query();

		query.addCriteria(Criteria.where("deportment").is(groupname));
		return mongoTemplate.find(query, Employee.class);

	}

	public List<Employee> sortbysalary() {
		Query query = new Query().with(new Sort("salary", "1"));


		return mongoTemplate.find(query, Employee.class);
	}
}
