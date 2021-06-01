package com.bazalytskyi.finalProject.db;

import com.bazalytskyi.finalProject.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {

    private static final String SQL__FIND_TEST_BY_SUBJECT =
            "SELECT * FROM tests WHERE subject_id=?";

    private static final String SQL__FIND_ALL_TESTS =
            "SELECT * FROM tests ";

    private static final String SQL__CREATE_TEST =
            "INSERT INTO tests (name, complexity, decision_time, subject_id) VALUES (?, ?, ?, ?)";

    private static final String SQL__FIND_TEST_BY_ID =
            "SELECT * FROM tests WHERE id=?";

    public List<Test> findAllTests() {
        List<Test> list = getList();
        PreparedStatement pstmt;
        TestMapper mapper = new TestMapper();
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_ALL_TESTS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    private ArrayList<Test> getList() {
        return new ArrayList<Test>();
    }

    public void createTest(Test test) {
        Connection con = null;
        try {
            PreparedStatement pstmt = con.prepareStatement(SQL__CREATE_TEST);
            con = DBManager.getInstance().getConnection();
            int field = 1;
            pstmt.setString(field++, test.getName());
            pstmt.setString(field++, test.getComplexity());
            pstmt.setTime(field++, Time.valueOf(test.getComplexity()));
            pstmt.setInt(field, test.getSubjectId());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    public List<Test> findTestsBySubject(int subjectId) {
        List<Test> list = getList();
        PreparedStatement pstmt;
        TestMapper mapper = new TestMapper();
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_TEST_BY_SUBJECT);
            pstmt.setInt(1, subjectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }


    private static class TestMapper implements EntityMapper {

        @Override
        public Test mapRow(ResultSet rs) {
            Test test = new Test();
            try {
                test.setId(rs.getInt(EntityFields.ENTITY__ID));
                test.setName(rs.getString(EntityFields.ENTITY__NAME));
                test.setComplexity(rs.getString(EntityFields.ENTITY__COMPLEXITY));
                test.setDecisionTime(rs.getString(EntityFields.ENTITY__DECISION_TIME));
                test.setSubjectId(rs.getInt(EntityFields.ENTITY__SUBJECT_ID));
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return test;
        }
    }
}
