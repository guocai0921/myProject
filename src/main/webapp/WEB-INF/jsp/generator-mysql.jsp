<%--
  Created by IntelliJ IDEA.
  User: sungu
  Date: 2018/11/7
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="generatorForm" class="generatorForm" method="post">
        <table cellpadding="5">

            <tr>
                <td>表名前缀:</td>
                <td><input class="easyui-textbox" type="text" name="tablePrefixName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>表名前缀:</td>
                <td><input class="easyui-textbox" type="text" name="tableName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>包名:</td>
                <td><input class="easyui-textbox" type="text" name="packageName" placeholder="格式:com.guocai.user" data-options="required:true"
                           style="width: 280px;"></input></td><td><span style="color: red">*格式:com.guocai.user</span></td>
            </tr>
            <tr>
                <td>实体类名:</td>
                <td><input class="easyui-textbox" type="text" name="entityName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
           <%-- <tr>
                <td>实体描述:</td>
                <td><input class="easyui-textbox" type="text" name="tableName" data-options="required:true"
                           style="width: 280px;"></input></td><span>格式:com.guocai.user</span>
            </tr>--%>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>
<script type="text/javascript">
    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#generatorForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }

        //ajax的post方式提交表单
        $.post("generator/submit",$("#generatorForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','生成成功!');
            }
        });

    }
</script>
