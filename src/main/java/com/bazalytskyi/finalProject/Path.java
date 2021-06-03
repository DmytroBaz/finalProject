package com.bazalytskyi.finalProject;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE__LOGIN = "/login.jsp";
	public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE__ADMIN_ALL_USERS = "/WEB-INF/jsp/admin/list_users.jsp";
	public static final String PAGE__USER_TESTS = "/WEB-INF/jsp/client/list_tests.jsp";

	// commands
	public static final String COMMAND__USER_MENU = "/controller?command=listTests";
	public static final String COMMAND__ADMIN_MENU = "/controller?command=listUsers";
	public static final String COMMAND__BLOCK_USER = "/controller?command=blockUser";
	public static final String COMMAND__UNBLOCK_USER = "/controller?command=unblockUser";

}