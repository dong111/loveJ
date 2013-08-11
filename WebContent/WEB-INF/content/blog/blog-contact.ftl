<#compress>
<#import "blog/lib/common.ftl" as com>
<#--title定义-->
<#assign title><@s.message code="blog.contact"/></#assign>

<#escape x as x?html>
<@com.page title='${title}'>

<#-- form验证 -->
<link rel="stylesheet" href="${rc.contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-${(rc.locale)!'zh_CN'}.js" type="text/javascript" charset="utf-8"></script>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        // binds form submission and fields to the validation engine
        jQuery("#formID").validationEngine();
    });
    
    jQuery(document).ready(function(){
    	$("#refresh").click(function(){
    		$("#captcha").attr("src", "${rc.contextPath}/Captcha.jpg");
    		return false;
    	});
    });
</script>
<div class="article">
  <div class="love-article-content">
  <#noescape>${siteConfig.contactDescription}</#noescape>
  </div>
</div>
<hr id="cmts"/>
<div class="article">
  <h2><span><@s.message code="blog.reply.contact"/></span><font color="red" style="font-weight:bold;"><#if success??><@s.message code="action.contact.add"/><#elseif failure??><@s.message code="form.code.wrong"/></#if></font></h2><div class="clr"></div>
  <form action="contact" class="jNice" method="POST" id="formID">
  <ol><li>
    <label for="contact.name"><@s.message code="contact.name"/><font color="red">*</font></label>
    <input id="name" name="name" class="text validate[required,maxSize[50]]" value="${(contact.name)!""}"/>
  </li><li>
    <label for="contact.email"><@s.message code="contact.email"/><font color="red">*</font></label>
    <input id="email" name="email" class="text validate[required,maxSize[255],custom[email]]" value="${(contact.email)!""}"/>
  </li><li>
    <label for="contact.site"><@s.message code="contact.site"/>(http://)</label>
    <input id="website" name="site" class="text validate[optional,maxSize[255],custom[url]]" value="${(contact.site)!""}"/>
  </li><li>
  </li><li>
    <label for="code"><@s.message code="form.code"/><font color="red">*</font></label>
    <input id="j_captcha_response" name="j_captcha_response" class="text validate[required,maxSize[10]]" />
    <label for="code"><img id="captcha" src="${rc.contextPath}/Captcha.jpg" border="0">&nbsp;&nbsp;<a id="refresh" href="#"><@s.message code="form.code.refresh"/></a></label>
  </li><li>
    <label for="contact.content"><@s.message code="contact.content"/><font color="red">*</font></label>
    <textarea id="message" name="content" rows="8" cols="50" class="validate[required,maxSize[500]]">${(contact.content)!""}</textarea>
  </li><li>
    <input type="image" src="${rc.contextPath}/styles/blog/images/submit.gif" class="send" />
    <div class="clr"></div>
  </li></ol>
  </form>
</div>
      
</@com.page>
</#escape>
</#compress>