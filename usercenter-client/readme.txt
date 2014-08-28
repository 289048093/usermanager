1. ��װShiro��Cas�ͻ���
   pom.xml����������չ��
	<dependency>
		<groupId>org.apache.shiro</groupId>
		<artifactId>shiro-cas</artifactId>
		<version>1.2.3</version>
	</dependency>


2. ����web.xml
	<listener>
        <listener-class>org.jasig.cas.client.session.SingleSignOutHttpSessionListener</listener-class>
    </listener>

    <filter>
        <filter-name>CAS Single Sign Out Filter</filter-name>
        <filter-class>org.jasig.cas.client.session.SingleSignOutFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CAS Single Sign Out Filter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <filter>
        <filter-name>ShiroFilter</filter-name>
        <filter-class>org.apache.shiro.web.servlet.IniShiroFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>ShiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

3. ��resourcesĿ¼�м���shiro.ini
    [main]
	casFilter = org.apache.shiro.cas.CasFilter
	casFilter.failureUrl = /error.jsp

	casRealm = org.apache.shiro.cas.CasRealm
	casRealm.defaultRoles = ROLE_USER
	# Cas������ṩ�ĵ�ַ
	casRealm.casServerUrlPrefix = https://channel.com:8888/cas

	# ���ط����ַ���������/shiro-cas
	casRealm.casService = http://channel.com:8080/app/shiro-cas
	casRealm.validationProtocol = SAML

	casSubjectFactory = org.apache.shiro.cas.CasSubjectFactory
	securityManager.subjectFactory = $casSubjectFactory

	# Cas������ṩ�ĵ����ַ
	user.loginUrl = https://channel.com:8888/cas/login?service=http://localhost:8080/app/shiro-cas

	[urls]
	/shiro-cas = casFilter
	# only with SHIRO-373 featured added :
	#/auth/** = casAuthUser
	/user/** = user
	/logout = logout
	/** = anon

4. ����HTTPS֤�鵼��(CAS��������߻��)


�ο����ף�
http://shiro.apache.org/webapp-tutorial.html#project-setup