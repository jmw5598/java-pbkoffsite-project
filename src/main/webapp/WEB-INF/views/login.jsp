<%@include file="/WEB-INF/views/fragments/taglibs.jspf" %>
<%@ page session="false"%>

<html>
    <head>
        <title>Contacts Application</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="<c:url value="/resources/css/libs/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/libs/bootstrap-theme-yeti-custom.min.css" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/css/libs/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
    </head>
    <body>
    	<div class="container-fluid">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 center-login">
                    <img id="logo" class="center-block" src="<c:url value="resources/img/logo/pbklogo.png" />"/>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 center-login">
                    <div class="panel panel-default">
                        
                        <div class="panel-heading">
                            <h5>Login</h5>
                        </div>
                        
                        <div class="panel-body">
                            <form action="${loginUrl}" method="POST">
                                <div class="form-group">
                                    <label for="username">Username</label>
                                    <input class="form-control" id="username" name="username" type="text" />
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input class="form-control" id="password" name="password" type="password" />
                                </div>
                                <div class="form-group">
                                    <legend></legend>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <button class="btn btn-primary" role="button" type="submit">Login</button>
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
        
        <!-- JQUERY & BOOTSTRAP JS FILES -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/libs/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/js/script.js" />"></script>
    </body>
</html>