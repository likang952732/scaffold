(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-713de54e"],{"2d68":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("div",{class:e.queryShow?"btn-btm":"",staticStyle:{"text-align":"right"}},[e.queryShow?a("el-button",{attrs:{size:"mini",icon:"el-icon-caret-top"},on:{click:function(t){e.queryShow=!e.queryShow}}},[e._v("收起查询搜索")]):a("el-button",{attrs:{size:"mini",icon:"el-icon-caret-bottom"},on:{click:function(t){e.queryShow=!e.queryShow}}},[e._v("展开查询搜索")])],1),e._v(" "),a("transition",{attrs:{name:"sidebarLogoFade"}},[e.queryShow?a("el-form",{ref:"listQuery",attrs:{inline:!0,model:e.listQuery,"label-width":"110px"},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"资产编号:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产编号"},model:{value:e.listQuery.assetNo,callback:function(t){e.$set(e.listQuery,"assetNo",t)},expression:"listQuery.assetNo"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"资产名称:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产名称"},model:{value:e.listQuery.name,callback:function(t){e.$set(e.listQuery,"name",t)},expression:"listQuery.name"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"当前状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.curStatus,callback:function(t){e.$set(e.listQuery,"curStatus",t)},expression:"listQuery.curStatus"}},[a("el-option",{attrs:{value:0,label:"内部"}}),e._v(" "),a("el-option",{attrs:{value:1,label:"外部"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"销毁状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.delStatus,callback:function(t){e.$set(e.listQuery,"delStatus",t)},expression:"listQuery.delStatus"}},[a("el-option",{attrs:{value:0,label:"未销毁"}}),e._v(" "),a("el-option",{attrs:{value:1,label:"已销毁"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"是否异常:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.isAbnormal,callback:function(t){e.$set(e.listQuery,"isAbnormal",t)},expression:"listQuery.isAbnormal"}},[a("el-option",{attrs:{value:0,label:"否"}}),e._v(" "),a("el-option",{attrs:{value:1,label:"是"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"是否黑名单:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.isBlack,callback:function(t){e.$set(e.listQuery,"isBlack",t)},expression:"listQuery.isBlack"}},[a("el-option",{attrs:{value:0,label:"否"}}),e._v(" "),a("el-option",{attrs:{value:1,label:"是"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"外借状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.lendStatus,callback:function(t){e.$set(e.listQuery,"lendStatus",t)},expression:"listQuery.lendStatus"}},[a("el-option",{attrs:{value:1,label:"外借"}})],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"请选择部门:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.orgID,callback:function(t){e.$set(e.listQuery,"orgID",t)},expression:"listQuery.orgID"}},e._l(e.orgOptions,(function(e){return a("el-option",{key:e.orgID,attrs:{label:e.orgName,value:e.orgID}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"开始日期:"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择开始日期"},on:{change:e.dateChange},model:{value:e.listQuery.startDate,callback:function(t){e.$set(e.listQuery,"startDate",t)},expression:"listQuery.startDate"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"结束日期:"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择结束日期"},on:{change:e.dateEndChange},model:{value:e.listQuery.endDate,callback:function(t){e.$set(e.listQuery,"endDate",t)},expression:"listQuery.endDate"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item"},[a("el-button",{attrs:{size:"mini",icon:"el-icon-refresh"},on:{click:e.colse}},[e._v("重置")]),e._v(" "),e.queryShow?a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.getList}},[e._v("查询")]):e._e(),e._v(" "),e.queryShow?a("el-button",{attrs:{type:"warning",size:"mini",icon:"el-icon-download"},on:{click:e.exportExl}},[e._v("导出实时状态excel\n          ")]):e._e()],1)],1):e._e()],1)],1),e._v(" "),a("div",{staticClass:"table_content"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("span",[a("el-popover",{attrs:{placement:"bottom",width:"245",trigger:"click"}},[a("el-checkbox-group",{model:{value:e.colOptions,callback:function(t){e.colOptions=t},expression:"colOptions"}},e._l(e.colSelect,(function(e){return a("el-checkbox",{key:e,attrs:{label:e}})})),1),e._v(" "),a("el-button",{staticClass:"icon-btn",attrs:{slot:"reference",icon:"el-icon-s-operation"},slot:"reference"})],1)],1),e._v(" "),a("el-button",{staticClass:"btn-add",attrs:{type:"success",plain:"",size:"mini",disabled:0==e.selection.length},on:{click:e.setBlacklist}},[e._v("批量设置黑名单")]),e._v(" "),a("el-button",{staticClass:"btn-add",attrs:{type:"warning",size:"mini",icon:"el-icon-upload"},on:{click:e.upLoad}},[e._v("上传excel\n      ")]),e._v(" "),a("el-button",{staticClass:"btn-add",attrs:{type:"primary",size:"mini",plain:"",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("添加\n      ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,"header-cell-style":{background:"#EBEBEB",color:"#515a6e",padding:"6px 0 !important"},"tooltip-effect":"dark"},on:{"selection-change":e.handleSelectionChange}},[a("af-table-column",{attrs:{fixed:"left",type:"selection",selectable:e.checkSelectable}}),e._v(" "),e.colData[0].istrue?a("af-table-column",{attrs:{prop:"assetNo",fixed:"left",label:"资产编号",width:"150"}}):e._e(),e._v(" "),e.colData[1].istrue?a("af-table-column",{attrs:{prop:"name",label:"资产名称"}}):e._e(),e._v(" "),e.colData[2].istrue?a("af-table-column",{attrs:{prop:"model",label:"型号"}}):e._e(),e._v(" "),e.colData[3].istrue?a("af-table-column",{attrs:{prop:"roomName",label:"所在楼宇名称"}}):e._e(),e._v(" "),e.colData[4].istrue?a("af-table-column",{attrs:{prop:"blackReason",label:"黑名单原因"}}):e._e(),e._v(" "),e.colData[5].istrue?a("af-table-column",{attrs:{prop:"blackTime",label:"黑名单设置时间"}}):e._e(),e._v(" "),e.colData[6].istrue?a("af-table-column",{attrs:{prop:"brand",label:"品牌"}}):e._e(),e._v(" "),e.colData[7].istrue?a("af-table-column",{attrs:{prop:"curStatus",label:"当前状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"内部",1:"外部"}[t.row.curStatus])+"\n        ")]}}],null,!1,1470720307)}):e._e(),e._v(" "),e.colData[8].istrue?a("af-table-column",{attrs:{prop:"delReason",label:"销毁原因"}}):e._e(),e._v(" "),e.colData[9].istrue?a("af-table-column",{attrs:{prop:"delStatus",label:"销毁状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"未销毁",1:"已销毁"}[t.row.delStatus])+"\n        ")]}}],null,!1,1194141457)}):e._e(),e._v(" "),e.colData[10].istrue?a("af-table-column",{attrs:{prop:"delTime",label:"销毁时间"}}):e._e(),e._v(" "),e.colData[11].istrue?a("af-table-column",{attrs:{prop:"isAbnormal",label:"是否异常"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"否",1:"是"}[t.row.isAbnormal])+"\n        ")]}}],null,!1,3177653075)}):e._e(),e._v(" "),e.colData[12].istrue?a("af-table-column",{attrs:{prop:"isBlack",label:"是否黑名单"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"否",1:"是"}[t.row.isBlack])+"\n        ")]}}],null,!1,280406500)}):e._e(),e._v(" "),e.colData[13].istrue?a("af-table-column",{attrs:{prop:"largeClass",label:"设备大类"}}):e._e(),e._v(" "),e.colData[14].istrue?a("af-table-column",{attrs:{prop:"lendStart",label:"外借开始时间"}}):e._e(),e._v(" "),e.colData[15].istrue?a("af-table-column",{attrs:{prop:"lendEnd",label:"外借结束时间"}}):e._e(),e._v(" "),e.colData[16].istrue?a("af-table-column",{attrs:{prop:"lendStatus",label:"外借状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({1:"外借"}[t.row.lendStatus])+"\n        ")]}}],null,!1,682866376)}):e._e(),e._v(" "),e.colData[17].istrue?a("af-table-column",{attrs:{prop:"littleClass",label:"设备小类"}}):e._e(),e._v(" "),e.colData[18].istrue?a("af-table-column",{attrs:{prop:"orgName",label:"所属部门名称"}}):e._e(),e._v(" "),e.colData[19].istrue?a("af-table-column",{attrs:{prop:"period",label:"计量有效期"}}):e._e(),e._v(" "),e.colData[20].istrue?a("af-table-column",{attrs:{prop:"posAlarmType",label:"检测报警"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"不报警",1:"允许位置外报警",2:"不允许位置报警"}[t.row.posAlarmType])+"\n        ")]}}],null,!1,619436774)}):e._e(),e._v(" "),e.colData[21].istrue?a("af-table-column",{attrs:{prop:"secLevel",label:"设备密级"}}):e._e(),e._v(" "),e.colData[22].istrue?a("af-table-column",{attrs:{prop:"serialNo",label:"序列号"}}):e._e(),e._v(" "),e.colData[23].istrue?a("af-table-column",{attrs:{prop:"setDate",label:"编制日期"}}):e._e(),e._v(" "),e.colData[24].istrue?a("af-table-column",{attrs:{prop:"staff",label:"责任人"}}):e._e(),e._v(" "),e.colData[25].istrue?a("af-table-column",{attrs:{prop:"price",label:"价格(元)"}}):e._e(),e._v(" "),e.colData[26].istrue?a("af-table-column",{attrs:{prop:"reasonType",label:"异常原因"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n          "+e._s({0:"异常外出",1:"进出状态不匹配",2:"外出超过天数"}[t.row.reasonType])+"\n        ")]}}],null,!1,2324627302)}):e._e(),e._v(" "),e.colData[27].istrue?a("af-table-column",{attrs:{prop:"useOrg",label:"使用部门"}}):e._e(),e._v(" "),e.colData[28].istrue?a("af-table-column",{attrs:{prop:"description",label:"说明"}}):e._e(),e._v(" "),e.colData[29].istrue?a("af-table-column",{attrs:{prop:"remark",label:"备注"}}):e._e(),e._v(" "),e.colData[30].istrue?a("af-table-column",{attrs:{prop:"timeAdd",label:"添加时间"}}):e._e(),e._v(" "),e.colData[31].istrue?a("af-table-column",{attrs:{prop:"timeModify",label:"修改时间"}}):e._e(),e._v(" "),e.colData[32].istrue?a("af-table-column",{attrs:{prop:"timeStatus",label:"当前状态产生时间"}}):e._e(),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"120"},scopedSlots:e._u([{key:"default",fn:function(t){return 0==t.row.delStatus?[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-popover",{ref:"popover-"+t.row.id,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[e._v("确定删除该数据吗？")]),e._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){e.$refs["popover-"+t.row.id].doClose()}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("确定")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){e.$refs["popover-"+t.row.id].doShow()}},slot:"reference"},[e._v("删除")])],1),e._v(" "),a("el-dropdown",{attrs:{trigger:"click"}},[a("el-button",{attrs:{type:"text",size:"small"}},[e._v("更多")]),e._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("span",{on:{click:function(a){return e.handleRail(t.row)}}},[a("el-dropdown-item",[e._v("资产轨迹")])],1),e._v(" "),a("span",{on:{click:function(a){return e.handleDetails(t.row)}}},[a("el-dropdown-item",[e._v("设置黑名单")])],1)])],1)]:void 0}}],null,!0)})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.pageNum,limit:e.listQuery.pageSize},on:{"update:page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:limit":function(t){return e.$set(e.listQuery,"pageSize",t)},pagination:e.getList}})],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,"close-on-click-modal":!1,width:"50%"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"资产编号:",prop:"assetNo"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产编号"},model:{value:e.editForm.assetNo,callback:function(t){e.$set(e.editForm,"assetNo",t)},expression:"editForm.assetNo"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"资产名称:",prop:"name"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产名称"},model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"品牌:",prop:"brand"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"品牌"},model:{value:e.editForm.brand,callback:function(t){e.$set(e.editForm,"brand",t)},expression:"editForm.brand"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"设备大类:",prop:"largeClass"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"设备大类"},model:{value:e.editForm.largeClass,callback:function(t){e.$set(e.editForm,"largeClass",t)},expression:"editForm.largeClass"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"设备小类:",prop:"littleClass"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"设备小类"},model:{value:e.editForm.littleClass,callback:function(t){e.$set(e.editForm,"littleClass",t)},expression:"editForm.littleClass"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"设备密级:",prop:"secLevel"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"设备密级"},model:{value:e.editForm.secLevel,callback:function(t){e.$set(e.editForm,"secLevel",t)},expression:"editForm.secLevel"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"序列号:",prop:"serialNo"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"序列号"},model:{value:e.editForm.serialNo,callback:function(t){e.$set(e.editForm,"serialNo",t)},expression:"editForm.serialNo"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"使用部门:",prop:"useOrg"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"使用部门"},model:{value:e.editForm.useOrg,callback:function(t){e.$set(e.editForm,"useOrg",t)},expression:"editForm.useOrg"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"说明:",prop:"desc\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"说明",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.editForm.desc,callback:function(t){e.$set(e.editForm,"desc",t)},expression:"editForm.desc"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"当前状态:",prop:"curStatus"}},[a("el-radio-group",{model:{value:e.editForm.curStatus,callback:function(t){e.$set(e.editForm,"curStatus",t)},expression:"editForm.curStatus"}},[a("el-radio",{attrs:{label:0}},[e._v("内部")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("外部")])],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"所属部门:",prop:"orgID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},on:{change:e.orgChange},model:{value:e.editForm.orgID,callback:function(t){e.$set(e.editForm,"orgID",t)},expression:"editForm.orgID"}},e._l(e.orgOptions,(function(e){return a("el-option",{key:e.orgID,attrs:{label:e.orgName,value:e.orgID}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"责任人:",prop:"staff"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.staff,callback:function(t){e.$set(e.editForm,"staff",t)},expression:"editForm.staff"}},e._l(e.staffOptions,(function(e){return a("el-option",{key:e.fullName,attrs:{label:e.fullName,value:e.fullName}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"型号:",prop:"model"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"型号"},model:{value:e.editForm.model,callback:function(t){e.$set(e.editForm,"model",t)},expression:"editForm.model"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"计量有效期:",prop:"period"}},[a("el-input",{staticClass:"append-item",attrs:{placeholder:"计量有效期"},model:{value:e.editForm.period,callback:function(t){e.$set(e.editForm,"period",t)},expression:"editForm.period"}},[a("template",{slot:"append"},[e._v("天")])],2)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"价格:",prop:"price"}},[a("el-input",{staticClass:"append-item",attrs:{placeholder:"价格"},model:{value:e.editForm.price,callback:function(t){e.$set(e.editForm,"price",t)},expression:"editForm.price"}},[a("template",{slot:"append"},[e._v("元")])],2)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"检测报警:",prop:"posAlarmType"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.posAlarmType,callback:function(t){e.$set(e.editForm,"posAlarmType",t)},expression:"editForm.posAlarmType"}},[a("el-option",{attrs:{value:0,label:"不报警"}}),e._v(" "),a("el-option",{attrs:{value:1,label:"允许位置外报警"}}),e._v(" "),a("el-option",{attrs:{value:2,label:"不允许位置报警"}})],1)],1),e._v(" "),0!=e.editForm.posAlarmType?a("el-form-item",{staticClass:"form-item",attrs:{label:"资产进出口:",prop:"assetPos"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",multiple:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.assetPos,callback:function(t){e.$set(e.editForm,"assetPos",t)},expression:"editForm.assetPos"}},e._l(e.assetOptions,(function(e){return a("el-option",{key:e.id,attrs:{label:e.posName,value:e.id}})})),1)],1):e._e(),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"所在楼宇:",prop:0==e.editForm.curStatus?"roomID":""}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.roomID,callback:function(t){e.$set(e.editForm,"roomID",t)},expression:"editForm.roomID"}},e._l(e.roomOptions,(function(e){return a("el-option",{key:e.id,attrs:{label:e.roomName,value:e.id}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"编制日期:",prop:"setDate"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{clearable:"",type:"date","value-format":"yyyy-MM-dd",placeholder:"选择处理日期"},model:{value:e.editForm.setDate,callback:function(t){e.$set(e.editForm,"setDate",t)},expression:"editForm.setDate"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注:",prop:"remark"}},[a("el-input",{staticClass:"filter-item",attrs:{type:"textarea",autosize:{minRows:1,maxRows:4},placeholder:"备注"},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark\t"}})],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createData}},[e._v("\n        "+e._s("create"==e.dialogStatus?"添加":"保存")+"\n      ")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"上传文件",visible:e.loadFormVisible,"close-on-click-modal":!1,width:"50%"},on:{"update:visible":function(t){e.loadFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"130px"}},[a("el-form-item",{attrs:{label:"上传文件:"}},[a("el-upload",{ref:"upload",staticClass:"upload-demo",attrs:{action:"string","on-change":e.handleFileChange,"on-remove":e.handleRemove,"http-request":e.doUpload,"file-list":e.fileList,limit:1,name:"file","auto-upload":!1}},[a("el-button",{attrs:{slot:"trigger",icon:"el-icon-upload",size:"mini",type:"primary"},slot:"trigger"},[e._v("上传资产信息")]),e._v(" "),a("el-button",{staticStyle:{"padding-left":"40px"},attrs:{type:"text",icon:"el-icon-download",size:"small"},on:{click:e.downloadWad}},[e._v("下载资产导入模板")]),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传1个xlsx/excel文件")])],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.loadFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.clickUpload}},[e._v("提交")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"设置黑名单",visible:e.setFormVisible,width:"35%"},on:{"update:visible":function(t){e.setFormVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.formRules,"label-width":"130px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"\t设置动作:",prop:"setType"}},[a("el-radio-group",{model:{value:e.form.setType,callback:function(t){e.$set(e.form,"setType",t)},expression:"form.setType"}},[a("el-radio",{attrs:{label:0}},[e._v("取消黑名单")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("设置黑名单")])],1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"黑名单原因",prop:"blackReason"}},[a("el-input",{staticClass:"filter-item",attrs:{autocomplete:"off",placeholder:"黑名单原因",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.form.blackReason,callback:function(t){e.$set(e.form,"blackReason",t)},expression:"form.blackReason"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.setFormVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.submitSet}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"资产归还",visible:e.alsoFormVisible,width:"35%"},on:{"update:visible":function(t){e.alsoFormVisible=t}}},[a("el-form",{ref:"alsoForm",attrs:{model:e.alsoForm,rules:e.alsoRulesForm,"label-width":"130px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"归还时间:",prop:"returnTime"}},[a("el-date-picker",{staticClass:"filter-item",attrs:{type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择归还时间"},model:{value:e.alsoForm.returnTime,callback:function(t){e.$set(e.alsoForm,"returnTime",t)},expression:"alsoForm.returnTime"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注",prop:"remark"}},[a("el-input",{staticClass:"filter-item",attrs:{autocomplete:"off",placeholder:"备注",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.alsoForm.remark,callback:function(t){e.$set(e.alsoForm,"remark",t)},expression:"alsoForm.remark"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.alsoFormVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:e.submitAlso}},[e._v("确 定")])],1)],1),e._v(" "),0==e.fistFlag?a("amend-pwd"):e._e()],1)},l=[],i=(a("7f7f"),a("28a5"),a("b85c")),o=a("5530"),r=a("365c"),n=a("333d"),c=(a("a481"),a("6b54"),a("ac6a"),a("4360")),u=(a("c4e3"),a("bc3a")),m=a.n(u),d=a("5c96");function p(e){var t=window.location.href,a=-1!=t.indexOf("/dist")?t.replace("dist/index.html","".concat(e.url).concat(e.params?"?"+e.params:"")):"";m()({method:e.method,url:"".concat(a).concat(e.url).concat(e.params?"?"+e.params:""),responseType:"blob",headers:{"Content-Type":"application/json",Authorization:"Bearer "+c["a"].getters.token}}).then((function(t){if(200==t.status){var a=document.createElement("a"),s=new Blob([t.data],{type:"application/vnd.ms-excel"});return a.style.display="none",a.href=URL.createObjectURL(s),a.setAttribute("download",e.fileName),document.body.appendChild(a),a.click(),document.body.removeChild(a),t}d["Message"].error({title:"失败",message:t.statusText})})).catch((function(e){return d["Message"].error({title:"失败",message:e.statusText}),e}))}a("0fd4");var f=a("2f62"),b={name:"registered",components:{Pagination:n["a"]},computed:Object(o["a"])({},Object(f["b"])(["name","fistFlag","userForm"])),data:function(){return{listQuery:{pageNum:1,pageSize:20,assetNo:"",curStatus:"",delStatus:0,isAbnormal:"",isBlack:"",lendStatus:"",name:"",startDate:"",endDate:"",orgID:""},loading:!0,queryShow:!0,list:[],total:0,dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{assetNo:"",curStatus:"",name:"",orgID:"",assetPos:"",brand:"",desc:"",largeClass:"",littleClass:"",model:"",period:"",posAlarmType:"",price:"",remark:"",roomID:"",secLevel:"",serialNo:"",setDate:"",staff:"",useOrg:""},editFormRules:{assetNo:[{required:!0,message:"资产编号不能为空",trigger:"blur"}],curStatus:[{required:!0,message:"当前状态不能为空",trigger:"blur"}],name:[{required:!0,message:"资产名称不能为空",trigger:"blur"}],orgID:[{required:!0,message:"所属部门不能为空",trigger:"blur"}],roomID:[{required:!0,message:"所在楼宇不能为空",trigger:"blur"}]},roomOptions:[],multipleSelection:[],selection:[],orgOptions:[],staffOptions:[],assetOptions:[],loadFormVisible:!1,fileList:[],form:{setType:"",blackReason:"",ids:""},formRules:{setType:[{required:!0,message:"设置动作不能为空",trigger:"blur"}],blackReason:[{required:!0,message:"黑名单原因不能为空",trigger:"blur"}]},setFormVisible:!1,alsoForm:{assetID:"",returnTime:"",remark:""},alsoRulesForm:{returnTime:[{required:!0,message:"归还时间不能为空",trigger:"blur"}]},alsoFormVisible:!1,colOptions:["资产编号","资产名称","型号","所在楼宇名称","黑名单原因","黑名单设置时间","品牌","当前状态","销毁状态","是否异常","是否黑名单","外借状态","所属部门名称","设备密级","责任人","价格(元)","使用部门"],colSelect:["资产编号","资产名称","型号","所在楼宇名称","黑名单原因","黑名单设置时间","品牌","当前状态","销毁原因","销毁状态","销毁时间","是否异常","是否黑名单","设备大类","外借开始时间","外借结束时间","外借状态","设备小类","所属部门名称","计量有效期","检测报警","设备密级","序列号","编制日期","责任人","价格(元)","异常原因","使用部门","说明","备注","添加时间","修改时间","当前状态产生时间"],colData:[{title:"资产编号",istrue:!0},{title:"资产名称",istrue:!0},{title:"型号",istrue:!0},{title:"所在楼宇名称",istrue:!0},{title:"黑名单原因",istrue:!0},{title:"黑名单设置时间",istrue:!1},{title:"品牌",istrue:!0},{title:"当前状态",istrue:!0},{title:"销毁原因",istrue:!1},{title:"销毁状态",istrue:!0},{title:"销毁时间",istrue:!1},{title:"是否异常",istrue:!0},{title:"是否黑名单",istrue:!0},{title:"设备大类",istrue:!1},{title:"外借开始时间",istrue:!1},{title:"外借结束时间",istrue:!1},{title:"外借状态",istrue:!0},{title:"设备小类",istrue:!1},{title:"所属部门名称",istrue:!0},{title:"计量有效期",istrue:!1},{title:"检测报警",istrue:!1},{title:"设备密级",istrue:!0},{title:"序列号",istrue:!1},{title:"编制日期",istrue:!1},{title:"责任人",istrue:!0},{title:"价格(元)",istrue:!0},{title:"异常原因",istrue:!1},{title:"使用部门",istrue:!0},{title:"说明",istrue:!1},{title:"备注",istrue:!1},{title:"添加时间",istrue:!1},{title:"修改时间",istrue:!1},{title:"当前状态产生时间",istrue:!1}]}},watch:{colOptions:function(e){var t=this,a=this.colSelect.filter((function(t){return e.indexOf(t)<0}));this.colData.filter((function(e){-1!=a.indexOf(e.title)?(e.istrue=!1,t.$nextTick((function(){t.$refs.table.doLayout()}))):(e.istrue=!0,t.$nextTick((function(){t.$refs.table.doLayout()})))}))}},created:function(){this.getList(),this.roomGetList(),this.orgGetList(),this.assetGetList()},methods:{colse:function(){this.listQuery={pageNum:1,pageSize:20,assetNo:"",curStatus:"",delStatus:0,isAbnormal:"",isBlack:"",lendStatus:"",name:"",startDate:"",endDate:"",orgID:""}},getList:function(){var e=this;this.loading=!0,Object(r["c"])("asset/page",this.listQuery).then((function(t){e.loading=!1,e.list=t.data.list,e.total=t.data.total})).catch((function(t){e.loading=!1,e.list=[],e.total=0}))},handleSelectionChange:function(e){this.selection=[],this.multipleSelection=e;var t,a=Object(i["a"])(this.multipleSelection);try{for(a.s();!(t=a.n()).done;){var s=t.value;this.selection.push(s.id)}}catch(l){a.e(l)}finally{a.f()}},handleAlso:function(e){var t=this;this.alsoFormVisible=!0,this.alsoForm={assetID:e.id,returnTime:"",remark:""},this.$nextTick((function(){t.$refs["alsoForm"].clearValidate()}))},handleRun:function(e){var t=this;this.$prompt("RFID打印编号","提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputPlaceholder:"请输入RFID打印编号",showInput:!0,inputValidator:function(e){if(null==e||""==e)return!1},inputErrorMessage:"RFID打印编号不能为空"}).then((function(a){var s=a.value;null!=s&&"undefined"!==typeof s&&void 0!=s||(s="");var l={assetID:e.id,rfidPrintNo:s};Object(r["c"])("asset/bindRFID",l).then((function(e){200==e.code?(t.getList(),t.$message.success({message:"绑定成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))})).catch((function(){}))},handleJobLog:function(e){var t=this;this.$prompt("销毁原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputPlaceholder:"请输入销毁原因",showInput:!0,inputValidator:function(e){if(null==e||""==e)return!1},inputErrorMessage:"销毁原因不能为空"}).then((function(a){var s=a.value;null!=s&&"undefined"!==typeof s&&void 0!=s||(s="");var l={ids:e.id,delReason:s};Object(r["c"])("asset/destroy",l).then((function(e){200==e.code?(t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))})).catch((function(){}))},handleDetails:function(e){var t=this;this.setFormVisible=!0,this.form={ids:e.id,setType:1,blackReason:""},this.$nextTick((function(){t.$refs["form"].clearValidate()}))},destruction:function(){var e=this;this.$prompt("销毁原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputPlaceholder:"请输入销毁原因",showInput:!0,inputValidator:function(e){if(null==e||""==e)return!1},inputErrorMessage:"销毁原因不能为空"}).then((function(t){var a=t.value;null!=a&&"undefined"!==typeof a&&void 0!=a||(a="");var s={ids:e.selection.join(","),delReason:a};Object(r["c"])("asset/destroy",s).then((function(t){200==t.code?(e.getList(),e.$message.success({message:"提交成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))})).catch((function(){}))},setBlacklist:function(){this.setFormVisible=!0,this.form={ids:this.selection.join(","),setType:1,blackReason:""}},submitSet:function(){var e=this;this.$refs["form"].validate((function(t){t&&Object(r["c"])("asset/setBlackList",e.form).then((function(t){200==t.code?(e.setFormVisible=!1,e.getList(),e.$message.success({message:"提交成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},submitAlso:function(){var e=this;this.$refs["alsoForm"].validate((function(t){t&&Object(r["c"])("asset/assetReturn",e.alsoForm).then((function(t){200==t.code?(e.alsoFormVisible=!1,e.getList(),e.$message.success({message:"提交成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},handleAdd:function(){var e=this;this.dialogStatus="create",this.dialogFormVisible=!0,this.editForm={assetNo:"",curStatus:0,name:"",orgID:"",assetPos:"",brand:"",desc:"",largeClass:"",littleClass:"",model:"",period:"",posAlarmType:"",price:"",remark:"",roomID:"",secLevel:"",serialNo:"",setDate:"",staff:"",useOrg:""},this.$nextTick((function(){e.$refs["editForm"].clearValidate()}))},handleEdit:function(e,t){var a=this;if(t)for(var s in t)null!=t[s]&&"undefined"!==typeof t[s]&&void 0!=t[s]||(t[s]="");this.dialogStatus="update",this.dialogFormVisible=!0,t.assetID?this.editForm.assetPos=t.assetID.split(","):this.editForm.assetPos="",this.editForm=Object.assign({},t),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["editForm"].validate((function(t){t&&Object(r["c"])("asset/".concat("create"==e.dialogStatus?"add":"update"),e.editForm).then((function(t){200==t.code?(e.dialogFormVisible=!1,e.getList(),e.$message.success({message:"create"==e.dialogStatus?"提交成功":"保存成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},upLoad:function(){this.loadFormVisible=!0,this.fileList=[]},handleDel:function(e,t){var a=this;Object(r["c"])("asset/delete/"+t.id).then((function(s){a.$message.success({type:"success",message:"删除成功"}),a.list.splice(e,1),a.total--,a.$refs["popover-"+t.id].doClose()})).catch((function(e){a.$refs["popover-"+t.id].doClose(),a.$message.error(e.message)}))},roomGetList:function(){var e=this;Object(r["c"])("room/list").then((function(t){e.roomOptions=t.data})).catch((function(t){e.roomOptions=[]}))},exportExl:function(){var e={method:"post",url:"asset/status/export",fileName:"资产实时状态.xls",params:"assetNo=".concat(this.listQuery.assetNo,"&name=").concat(this.listQuery.name,"&startDate=").concat(this.listQuery.startDate,"&endDate=").concat(this.listQuery.endDate)};p(e)},orgGetList:function(){var e=this;Object(r["c"])("user/getOrg").then((function(t){e.orgOptions=t.data})).catch((function(t){e.orgOptions=[]}))},orgChange:function(e){var t=this;Object(r["c"])("personnellib/list",{orgID:e}).then((function(e){t.staffOptions=e.data})).catch((function(e){t.staffOptions=[]}))},assetGetList:function(){var e=this;Object(r["c"])("entrypos/list").then((function(t){e.assetOptions=t.data})).catch((function(t){e.assetOptions=[]}))},clickUpload:function(){console.log(this.fileList),0!=this.fileList.length?this.$refs.upload.submit():this.$message.error("请上传文件！")},handleFileChange:function(e,t){var a=e.name.substring(e.name.lastIndexOf(".")+1);if("xlsx"!==a&&"xls"!==a)return this.$refs.upload.clearFiles(),this.$notify.warning({title:"警告",message:"只能上传Excel（即后缀是.xlsx或.xls）的文件"}),!1;this.fileList.push(e),this.$refs.editForm.validateField("fileList")},handleRemove:function(e,t){console.log(e,t),this.fileList=[]},doUpload:function(e){var t=this;this.$refs["editForm"].validate((function(a){if(a){var s=new FormData;s.append("file",e.file),Object(r["a"])("asset/excelImport",s).then((function(e){200==e.code?(t.getList(),t.loadFormVisible=!1,t.$message.success({type:"success",message:"上传成功"}),t.$refs.upload.clearFiles(),t.fileList=[]):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))}}))},handleRail:function(e){this.$router.push({path:"trajectory",query:{id:e.id}})},downloadWad:function(){var e={method:"get",url:"asset/getTemplate",fileName:"资产模板.xls"};p(e)},dateChange:function(e){var t=this;this.$nextTick((function(){null!=e&&"undefined"!==typeof e&&void 0!=e||(t.listQuery.startDate="")}))},dateEndChange:function(e){var t=this;this.$nextTick((function(){null!=e&&"undefined"!==typeof e&&void 0!=e||(t.listQuery.endDate="")}))},checkSelectable:function(e){return 0==e.delStatus}}},v=b,h=(a("9a81"),a("2877")),g=Object(h["a"])(v,s,l,!1,null,"70901e83",null);t["default"]=g.exports},"333d":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},l=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,s){return e/=s/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var i=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function o(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function r(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function n(e,t,a){var s=r(),l=e-s,n=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=n;var r=Math.easeInOutQuad(c,s,l,t);o(r),c<t?i(e):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&n(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},u=c,m=(a("fdfd"),a("2877")),d=Object(m["a"])(u,s,l,!1,null,"d2983402",null);t["a"]=d.exports},6078:function(e,t,a){},"9a81":function(e,t,a){"use strict";a("dc59")},dc59:function(e,t,a){},fdfd:function(e,t,a){"use strict";a("6078")}}]);