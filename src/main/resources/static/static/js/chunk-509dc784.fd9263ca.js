(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-509dc784"],{"094d":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:e.listQuery,"label-width":"100px"},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"姓名:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"姓名"},model:{value:e.listQuery.fullName,callback:function(t){e.$set(e.listQuery,"fullName",t)},expression:"listQuery.fullName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"手机号:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"手机号"},model:{value:e.listQuery.phone,callback:function(t){e.$set(e.listQuery,"phone",t)},expression:"listQuery.phone"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"选择部门:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:e.listQuery.orgID,callback:function(t){e.$set(e.listQuery,"orgID",t)},expression:"listQuery.orgID"}},e._l(e.orgOptions,(function(e){return a("el-option",{key:e.orgID,attrs:{label:e.orgName,value:e.orgID}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item"},[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),e._v(" "),a("div",{staticClass:"btn-btm"},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("添加\n    ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"fullName",fixed:"left",label:"姓名"}}),e._v(" "),a("af-table-column",{attrs:{prop:"jobNo",label:"工号"}}),e._v(" "),a("af-table-column",{attrs:{prop:"orgName",label:"所属部门"}}),e._v(" "),a("af-table-column",{attrs:{prop:"phone",label:"手机号"}}),e._v(" "),a("af-table-column",{attrs:{prop:"status",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"显示",1:"不显示"}[t.row.status])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"备注"}}),e._v(" "),a("af-table-column",{attrs:{prop:"createTime",label:"创建时间"}}),e._v(" "),a("af-table-column",{attrs:{prop:"updateTime",label:"修改时间"}}),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-popover",{ref:"popover-"+t.row.orgID,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[e._v("确定删除该数据吗？")]),e._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){e.$refs["popover-"+t.row.orgID].doClose()}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("确定")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){e.$refs["popover-"+t.row.orgID].doShow()}},slot:"reference"},[e._v("删除")])],1)]}}])})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.pageNum,limit:e.listQuery.pageSize},on:{"update:page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:limit":function(t){return e.$set(e.listQuery,"pageSize",t)},pagination:e.getList}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,"close-on-click-modal":!1,width:"35%"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"130px"}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"部门名称:",prop:"orgID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1,"popper-class":"select-popper"},model:{value:e.editForm.orgID,callback:function(t){e.$set(e.editForm,"orgID",t)},expression:"editForm.orgID"}},e._l(e.orgOptions,(function(e){return a("el-option",{key:e.orgID,attrs:{label:e.orgName,value:e.orgID}})})),1)],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"姓名:",prop:"fullName"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"姓名"},model:{value:e.editForm.fullName,callback:function(t){e.$set(e.editForm,"fullName",t)},expression:"editForm.fullName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"\t手机号:",prop:"phone"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"手机号"},model:{value:e.editForm.phone,callback:function(t){e.$set(e.editForm,"phone",t)},expression:"editForm.phone"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"工号:",prop:"jobNo"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"工号"},model:{value:e.editForm.jobNo,callback:function(t){e.$set(e.editForm,"jobNo",t)},expression:"editForm.jobNo"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"备注:",prop:"remark\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"备注",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createData}},[e._v("\n        "+e._s("create"==e.dialogStatus?"添加":"保存")+"\n      ")])],1)],1),e._v(" "),0==e.fistFlag?a("amend-pwd"):e._e()],1)},o=[],r=a("5530"),l=a("365c"),n=a("333d"),s=a("ca17"),c=a.n(s),u=(a("542c"),a("2f62")),d={name:"library",computed:Object(r["a"])({},Object(u["b"])(["name","fistFlag","userForm"])),components:{Pagination:n["a"],Treeselect:c.a},data:function(){var e=function(e,t,a){/^((1[3,5,8,7,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[1,7]))\d{8}$/.test(t)?a():a(new Error("手机号有误，请重新填写"))};return{loading:!0,list:[],total:0,listQuery:{pageNum:1,pageSize:20,fullName:"",orgID:null,phone:""},dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{fullName:"",jobNo:"",orgID:"",phone:"",remark:""},editFormRules:{fullName:[{required:!0,message:"姓名不能为空",trigger:"blur"}],jobNo:[{required:!0,message:"工号不能为空",trigger:"blur"}],orgID:[{required:!0,message:"部门名称不能为空",trigger:"blur"}],phone:[{required:!0,message:"手机号不能为空",trigger:"blur"},{required:!0,validator:e,trigger:"blur"}]},orgOptions:[]}},created:function(){this.getList(),this.getTreeList()},methods:{getList:function(){var e=this;this.loading=!0,Object(l["c"])("personnellib/page",this.listQuery).then((function(t){e.loading=!1,e.list=t.data.list,e.total=t.data.total})).catch((function(t){e.loading=!1,e.list=[],e.total=0}))},getTreeList:function(){var e=this;Object(l["c"])("user/getOrg").then((function(t){e.orgOptions=t.data})).catch((function(t){e.orgOptions=[]}))},handleAdd:function(){var e=this;this.dialogStatus="create",this.dialogFormVisible=!0,this.editForm={fullName:"",jobNo:"",orgID:"",phone:"",remark:""},this.$nextTick((function(){e.$refs["editForm"].clearValidate()}))},handleEdit:function(e,t){var a=this;if(t)for(var i in t)null!=t[i]&&"undefined"!==typeof t[i]&&void 0!=t[i]||(t[i]="");this.dialogStatus="update",this.dialogFormVisible=!0,this.editForm=Object.assign({},t),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["editForm"].validate((function(t){t&&Object(l["c"])("personnellib/".concat("create"==e.dialogStatus?"add":"update"),e.editForm).then((function(t){200==t.code?(e.dialogFormVisible=!1,e.getList(),e.$message.success({message:"create"==e.dialogStatus?"提交成功":"保存成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))}))},handleDel:function(e,t){var a=this;Object(l["c"])("personnellib/delete/"+t.orgID).then((function(i){a.$message.success({type:"success",message:"删除成功"}),a.list.splice(e,1),a.total--,a.$refs["popover-"+t.orgID].doClose()})).catch((function(e){a.$refs["popover-"+t.orgID].doClose(),a.$message.error(e.message)}))},normalizer:function(e){return e.children&&!e.children.length&&delete e.children,{id:e.orgID,label:e.orgName,children:e.children}}}},p=d,m=a("2877"),f=Object(m["a"])(p,i,o,!1,null,"8f780b70",null);t["default"]=f.exports},"333d":function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},o=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,i){return e/=i/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var r=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function l(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function n(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function s(e,t,a){var i=n(),o=e-i,s=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=s;var n=Math.easeInOutQuad(c,i,o,t);l(n),c<t?r(e):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&s(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&s(0,800)}}},u=c,d=(a("e498"),a("2877")),p=Object(d["a"])(u,i,o,!1,null,"6af373ef",null);t["a"]=p.exports},7456:function(e,t,a){},e498:function(e,t,a){"use strict";a("7456")}}]);