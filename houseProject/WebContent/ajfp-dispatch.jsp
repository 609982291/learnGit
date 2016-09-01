<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript">
	KE.show({
		id : 'content7',
		cssPath : './index.css'
	});
</script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>
</head>
<body>
	<s:form action="ajfp-dispatch" method="post" theme="simple">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">信访登记</a></li>
			</ul>
		</div>
		<div class="formbody">
			<div id="usual1" class="usual">

				<div id="tab1" class="tabson">
					<div class="formtext">您好，请在此处选择受理人！<s:hidden name="id"></s:hidden></div>
					<ul class="forminfo">
						<li><label>受理人<b>*</b></label>
							<div class="usercity">
								<div class="cityleft">
									<s:select name="undertakeUserId" list="#request.ajfpusers" class="select2" listKey="id" listValue="userName">
									</s:select>
								</div>
								
							</div></li>
						
						
						<li><label>&nbsp;</label>
						<s:submit  class="btn" value="确认保存" onclick="return checkData()"/>		
								
						</li>
					</ul>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>