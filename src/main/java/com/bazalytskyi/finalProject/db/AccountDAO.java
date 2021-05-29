package com.bazalytskyi.finalProject.db;

import com.bazalytskyi.finalProject.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDAO {

    private static final String SQL__FIND_ACCOUNT_BY_LOGIN =
            "SELECT * FROM accounts WHERE login=?";

    private static final String SQL__FIND_ACCOUNT_BY_ID =
            "SELECT * FROM accounts WHERE id=?";

    private static final String SQL_UPDATE_ACCOUNT =
            "UPDATE accounts SET firstName=?, lastName=?, login=?, password=?, active=?, localeName=?" +
                    "	WHERE id=?";


    public Account findAccount(int id) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL__FIND_ACCOUNT_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return account;
    }

    public Account findAccountByLogin(String login) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL__FIND_ACCOUNT_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return account;
    }

    public void updateAccount(Account account) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            int field = 1;
            pstmt.setString(field++, account.getFirstName());
            pstmt.setString(field++, account.getLastName());
            pstmt.setString(field++, account.getLogin());
            pstmt.setString(field++, account.getPassword());
            pstmt.setBoolean(field++, account.isActive());
            pstmt.setString(field++, account.getLocaleName());
            pstmt.setInt(field, account.getId());
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(con);
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private static class AccountMapper implements EntityMapper {

        @Override
        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getInt(EntityFields.ENTITY__ID));
                account.setFirstName(rs.getString(EntityFields.ENTITY__FIRST_NAME));
                account.setLastName(rs.getString(EntityFields.ENTITY__LAST_NAME));
                account.setLogin(rs.getString(EntityFields.ENTITY__LOGIN));
                account.setPassword(rs.getString(EntityFields.ENTITY__PASSWORD));
                account.setActive(rs.getBoolean(EntityFields.ENTITY__IS_ACTIVE));
                account.setLocaleName(rs.getString(EntityFields.ENTITY__LOCALE_NAME));
                account.setRoleId(rs.getInt(EntityFields.ENTITY__ROLE));

                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
