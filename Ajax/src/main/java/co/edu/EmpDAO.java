package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DAO {

	// 리스트
	public List<Employee> empList() {
		connect();
		List<Employee> list = new ArrayList<Employee>();
		try {
			psmt = conn.prepareStatement("SELECT * FROM EMP_TEMP ORDER BY 1");
			rs = psmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

//	
//	
//	
//	
//	
	// 입력
	public Employee insertEmp(Employee emp) {
		String sql = "INSERT INTO EMP_TEMP (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, JOB_ID, HIRE_DATE)"
				+ " VALUES(?,?,?,?,?,?)";
		String seqSql = "SELECT EMPLOYEES_SEQ.NEXTVAL FROM DUAL";

		connect();
		int nextSeq = -1;
		try {
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				nextSeq = rs.getInt(1);
			}

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextSeq);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getJobId());
			psmt.setString(6, emp.getHireDate());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨");

			emp.setEmployeeId(nextSeq);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return emp;

	}

	// 수정
	public Employee updateEmp(Employee emp) {
		connect();
		String sql = "UPDATE EMP_TEMP SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, HIRE_DATE=? WHERER EMPLOYEE_ID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정됨");
			if (r > 0) { // 정상 실행시
				return emp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null; // 비정상 실행시
	}

	// 삭제

	// 한 건 조회

}