<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${pageContext.request.locale.language}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><fmt:message key="html.head.title" /></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/ext-4.1.1a/resources/css/ext-all.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ext-4.1.1a/ext-all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/ext-4.1.1a/locale/ext-lang-ko.js"></script>
	
	<script type="text/javascript">
	<!--
	Ext.onReady(function(){
	    Ext.QuickTips.init();
	    
	    var checkLogin = function() {
	    	Ext.Ajax.request({
	    		url: '/login.do',
	    		method: 'POST',
	    		scope: this,
	    		params: {
	    			userId: Ext.getCmp('userId').getValue(),
	    			password: Ext.getCmp('password').getValue()
	    		},
	    		success: function(response) {
	    			console.log('response : ' +response.responseText);
	    			
	    			if (response.responseText == 'success') {
	    				window.location = '/home.do';
	    			} else {
	    				alert("존재하지않는 사용자이거나, 비밀번호가 틀립니다.");
	    			}
	    		},
	    		// Exception에 일어났을 경우 Exception에 대한 내용을 출력한다.
	    		failure: function(response) {
	    			alert("Access failure (responseText): " + response.responseText);
	    		    alert("Access failure (status): " + response.status);
	    		}
	    	});
	    };
	    
	    Ext.create('Ext.form.Panel', {
	    	width: 245,
	    	height: 120,
	    	x: (window.document.width / 2) - 120,
	    	y: (window.document.height / 2) - 100,
	    	id: 'main-panel',
	    	frame:true, 
	        title:'Please Login..', 
	        defaultType:'textfield',
	        layout: 'vbox',
			items: [{
				fieldLabel: 'LOGIN ID',
				name: 'userId',
				id: 'userId',
				allowBlank: false,
			}, {
				fieldLabel: 'PASSWORD',
				name: 'password',
				id: 'password',
				inputType: 'password',
				allowBlank: false
			}],
			buttons: [{
				text: 'Login',
				formBind: true,
				handler: function() { checkLogin(); }
			}],
			listeners: {
				render: function(panel) {
					panel.el.on('keypress',panel.keyHandler);
				}
			},
			keyHandler: function(e) {
				var key = e.getKey();
	            
				if (key === e.ENTER) {
					checkLogin();
				}
			}
	    }).render(document.body);
	});
	-->
	</script>
</head>
<body>
</body>
</html>