<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
        <h1>Hello world!</h1>
        <p>The time on the server is ${serverTime}.</p>
        <p>Username is ${userName}.</p>
        <p>
            <form:form action="${pageContext.request.contextPath}/logout">
                <button>Logout</button>
            </form:form>
        </p>
    </div>
</body>
</html>
