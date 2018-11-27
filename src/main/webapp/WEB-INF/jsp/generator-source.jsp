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
    <form id="generatorSourceForm" class="generatorSourceForm" method="post">
        <table cellpadding="5">

            <tr>
                <td>数据源描述:</td>
                <td><input class="easyui-textbox" type="text" name="dbDesc" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库类别:</td>
                <td><input class="easyui-textbox" type="text" name="dbName" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库驱动:</td>
                <td><input class="easyui-textbox" type="text" name="dbDriverName" placeholder="格式:com.guocai.user" data-options="required:true"
                           style="width: 280px;"></input></td><td><span style="color: red">*格式:com.guocai.user</span></td>
            </tr>
            <tr>
                <td>数据库IP:</td>
                <td><input class="easyui-textbox" type="text" name="dbHost" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库端口:</td>
                <td><input class="easyui-textbox" type="text" name="dbPort" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库名称:</td>
                <td><input class="easyui-textbox" type="text" name="dbSid" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库驱动模式:</td>
                <td><input class="easyui-textbox" type="text" name="dbDriver" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库用户名:</td>
                <td><input class="easyui-textbox" type="text" name="dbUsername" data-options="required:true"
                           style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>数据库密码:</td>
                <td><input class="easyui-textbox" type="text" name="dbPassword" data-options="required:true"
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
        if (!$('#generatorSourceForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }

        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };

        $.ajax({
            url: "generator/source",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify($("#generatorSourceForm").serializeObject()),
            success: function (data) {
                if(data.status == 200){
                    $('#userAddForm').form('reset');
                    $.messager.alert('提示', data.msg);
                }
            }
        });

    }
</script>
