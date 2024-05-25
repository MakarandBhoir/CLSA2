Step 1: Run SpringBoot Application
Step 2: Access login controller http://localhost:9091/StudentApp/students/login.do
		It should be Post request and pass data in following format
		{
			"username": "makarand",
			"password": "makarand"
		}
Step 3: In return you will get JWT Token copy that
Step 4: http://localhost:9091/StudentApp/students/ method = Get OR Any controller method
Step 5: And set Authorization header = Bearer JWT_TOKEN