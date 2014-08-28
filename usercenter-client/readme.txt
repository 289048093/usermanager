1. 安装Shiro和Cas客户端
   pom.xml加入如下扩展点
	<dependency>
		<groupId>org.apache.shiro</groupId>
		<artifactId>shiro-cas</artifactId>
		<version>1.2.3</version>
	</dependency>


2. 配置web.xml
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

3. 在resources目录中加入shiro.ini
    [main]
	casFilter = org.apache.shiro.cas.CasFilter
	casFilter.failureUrl = /error.jsp

	casRealm = org.apache.shiro.cas.CasRealm
	casRealm.defaultRoles = ROLE_USER
	# Cas服务端提供的地址
	casRealm.casServerUrlPrefix = https://channel.com:8888/cas

	# 本地服务地址，后面加入/shiro-cas
	casRealm.casService = http://channel.com:8080/app/shiro-cas
	casRealm.validationProtocol = SAML

	casSubjectFactory = org.apache.shiro.cas.CasSubjectFactory
	securityManager.subjectFactory = $casSubjectFactory

	# Cas服务端提供的登入地址
	user.loginUrl = https://channel.com:8888/cas/login?service=http://localhost:8080/app/shiro-cas

	[urls]
	/shiro-cas = casFilter
	# only with SHIRO-373 featured added :
	#/auth/** = casAuthUser
	/user/** = user
	/logout = logout
	/** = anon

4. 导入HTTPS证书导入(CAS服务器这边获得)


参考文献：
http://shiro.apache.org/webapp-tutorial.html#project-setup