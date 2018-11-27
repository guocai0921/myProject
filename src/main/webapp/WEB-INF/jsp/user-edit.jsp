<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
    <form id="userEditForm" class="userForm" method="post">
        <input type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>姓名:</td>
                <td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true"
                           style="width: 200px;"></input></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input class="easyui-validatebox" name="password" data-options="required:true"
                           style="width: 200px;"></input></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input class="easyui-numberbox" type="text" name="age"
                           data-options="min:1,max:200,precision:0," style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input class="easyui-validatebox" type="text" name="email" validType="email"
                           style="width: 200px;"/></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td>
                    <input class="easyui-validatebox" type="text" name="phone"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <input class="easyui-combobox" type="text" id="sexs" name="sex" editable="false"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" id="regtime" name="regtime"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input class="easyui-datebox" type="text" id="hiredate" name="hiredate"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>住址:</td>
                <td>
                    <input class="easyui-validatebox" type="text" id="address" name="address"
                           style="width: 200px;"/>
                </td>
            </tr>
            <tr>
                <td>学历:</td>
                <td>
                    <input class="easyui-combobox" type="text" id="degrees" name="degree"
                           style="width: 200px;"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    </div>
</div>
<script type="text/javascript">

    $(function () {
        $('#sexs').combobox({
                data: [{code: "1", text: "男"}, {code: "2", text: "女"}],
                valueField: 'code',
                textField: 'text'
            }
        );
    });
    $(function () {
        $('#degrees').combobox({
                data: [
                    {code: "1", text: "小学"},
                    {code: "2", text: "初中"},
                    {code: "3", text: "高中"},
                    {code: "4", text: "大专"},
                    {code: "5", text: "本科"},
                    {code: "6", text: "硕士及以上"}
                ],
                valueField: 'code',
                textField: 'text'
            }
        );
    });

    function submitForm() {

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
            url: "user/edit",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify($("#userEditForm").serializeObject()),
            success: function (data) {
                if(data.status == 200){
                    $.messager.alert('提示', '修改信息成功!', 'info', function () {
                        $("#userEditWindow").window('close');
                        $("#userList").datagrid("reload");
                    });
                }
            }
        });

        // $.post("user/update", $("#userEditForm").serialize(), function (data) {
        //     if (data.status == 200) {
        //         $.messager.alert('提示', '修改信息成功!', 'info', function () {
        //             $("#userEditWindow").window('close');
        //             $("#userList").datagrid("reload");
        //         });
        //     }
        // });
    }
</script>
