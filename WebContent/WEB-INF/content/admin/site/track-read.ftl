<#compress>
<#import "admin/lib/common.ftl" as com/>
<#include "admin/lib/site-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.trackManage.manage"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<script>
$(document).ready(function(){
	
	var index;
	$("#createTrack").click(function(){
		var $trackTable = $("#trackTable");
		if (index == null) {
			var name = $trackTable.find("tr:last td:last input").attr("name");
			name = name.substring(name.indexOf('[')+1);
			index = name.substring(0,name.indexOf(']'));
		}
		index++;
		//alert(index);
		var $track_tr = $("<tr><td><input name='tracks[" + index + "].title'/></td>"+
				"<td><input name='tracks[" + index + "].location' style='width:250px;'/></td>"+
				"<td><input name='tracks[" + index + "].creator'/></td></tr>");
		$trackTable.append($track_tr);
	});	
});

   
</script>


<div id="main">
		<h3 style="text-align:center;color:red;">
			<#if success??><@s.message code="action.track.update"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<form action="updateTrack" namespace="/admin/site"  class="jNice" method="POST" id="formID">
    	<table cellpadding="0" cellspacing="0" id="trackTable">
			<tr style="font-weight:bold;">
				<td><@s.message code="track.title"/><font color="red">*</font></td>
				<td><@s.message code="track.location"/><font color="red">*</font></td>
            	<td><@s.message code="track.creator"/></td>
        	</tr>
        	<#if tracks?? && tracks?size gt 0>
        		<#list tracks as p>
        		<tr>
	        		<td><input name="tracks[${p_index}].title" value="${p.title}"/></td>
	            	<td><input name="tracks[${p_index}].location" value="${p.location}" style="width:250px;"/></td>
	            	<td><input name="tracks[${p_index}].creator" value="${p.creator}"/></td>
	        	</tr>
        		</#list>
        	<#else>
				<tr>
	        		<td><input name="tracks[0].title"/></td>
	            	<td><input name="tracks[0].location" style="width:250px;"/></td>
	            	<td><input name="tracks[0].creator" /></td>
	        	</tr>
        	</#if>
        </table>
        <fieldset style="border:none;">
        <input id="createTrack" type="button" value='<@s.message code="form.create"/>' />&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value='<@s.message code="form.save"/>' />
        </fieldset>
        </form>
        <br/>	
        <br/><br/>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>