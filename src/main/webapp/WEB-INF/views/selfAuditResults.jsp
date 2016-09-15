<%@include file="/WEB-INF/views/fragments/taglibs.jspf" %>
<%@ page session="false"%>

<html>
    <head>
        <title>Contacts Application</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <!-- INCLUDES ALL CSS FRAGMENT -->
        <%@include file="/WEB-INF/views/fragments/css.jspf" %>
        
    </head>
    <body>
		<div class="container-fluid">
            <div class="row">
                <div id="nav" class="col-xs-12 col-sm-12 col-md-3 col-lg-2">
                
                	<!-- INCLUDES NAVIGATION FRAGMENT -->
                	<%@include file="/WEB-INF/views/fragments/nav.jspf" %>
                	
                </div>
                <div class="col-xs-12 col-sm-12 col-md-9 col-lg-10 bg-gray-light padding-top">
                    
                    <!--  INCLUDES DASHBOARD FRAGMENT -->
                    <%@include file="/WEB-INF/views/fragments/shelfAuditResults.jspf" %>
                    
				</div>
            </div>
        </div>
        
       <%@include file="/WEB-INF/views/fragments/script.jspf" %>
        
        
	</body>
</html>