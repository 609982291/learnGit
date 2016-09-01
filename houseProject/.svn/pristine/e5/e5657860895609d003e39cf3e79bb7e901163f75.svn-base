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
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">业务督办</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">

			<div id="tab1" class="tabson">
				<div class="formtext">您好，请在此处填写约谈笔录信息！</div>
				<ul class="forminfo">
					<s:form action="under-upload" method="post"
						enctype="multipart/form-data" theme="simple">
						<li><label>企业相关资料<b>*</b></label> <s:hidden name="id"></s:hidden>
							<s:file name="file" label="">
							</s:file> <s:submit value="上传"></s:submit></li>
					</s:form>
					<li><s:form action="under-deleteFile" methos="post"
							theme="simple">
							<s:hidden name="id"></s:hidden>
							<s:checkboxlist list="#request.files" listKey="id"
								name="allfiles" listValue="attachmentOrgName" theme="simple"></s:checkboxlist>

							<s:submit value="删除"></s:submit>
						</s:form></li>

					<s:form action="under-handle" method="post" theme="simple">
						<li><label>约谈笔录<b>*</b></label> <s:textfield
								name="interviewRecord" type="text" class="dfinput"
								placeholder="请填写约谈笔录" /> <s:hidden name="id"></s:hidden>
						</li>

						<li><label>案件备注<b>*</b></label> <s:textarea name="caseRemark"
								id="xfdj-save_caseContent" cols="" rows="" class="textinput"></s:textarea></li>


						<li><label>&nbsp;</label> <s:submit class="btn" value="确认保存"
								onclick="return checkData()" /></li>
					</s:form>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>