<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>
<#--currentNav定义-->
<#assign currentNav><@s.message code="site.articleManage.update"/></#assign>
</#compress>
<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>
	<script src="${rc.contextPath}/styles/jqueryform/2.8/jquery.form.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#fileuploadForm").ajaxForm({ success: function(html) {
				$("#uploadMessage").replaceWith(html);
			}
		});
	});
</script>	
<div id="main">
	<p id="uploadMessage"></p>
	<form id="fileuploadForm" action="attach" class="jNice" method="POST" enctype="multipart/form-data">
		<fieldset>
			<input name="articleId" value="22" type="hidden"/>
			<p><input type="file" name="file" ></p>

			<input type="submit" value='<@s.message code="form.save"/>' />
		</fieldset>
	</form>
	
</div>
<#-- // #main -->
</@com.page>
</#escape>