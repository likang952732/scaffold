(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-259a4116"],{"333d":function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},r=[];a("c5f6");Math.easeInOutQuad=function(e,t,a,i){return e/=i/2,e<1?a/2*e*e+t:(e--,-a/2*(e*(e-2)-1)+t)};var o=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function l(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function n(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function s(e,t,a){var i=n(),r=e-i,s=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=s;var n=Math.easeInOutQuad(c,i,r,t);l(n),c<t?o(e):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&s(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&s(0,800)}}},u=c,d=(a("e498"),a("2877")),m=Object(d["a"])(u,i,r,!1,null,"6af373ef",null);t["a"]=m.exports},7456:function(e,t,a){},e498:function(e,t,a){"use strict";a("7456")},e8a0:function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:e.listQuery,"label-width":"110px"},nativeOn:{submit:function(e){e.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"部门名称:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"部门名称"},model:{value:e.listQuery.orgName,callback:function(t){e.$set(e.listQuery,"orgName",t)},expression:"listQuery.orgName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item"},[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:e.getList}},[e._v("查询")])],1)],1)],1),e._v(" "),a("div",{staticClass:"btn-btm"},[a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.handleAdd}},[e._v("添加\n    ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:e.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"},"row-key":"orgID","default-expand-all":"","tree-props":{children:"children",hasChildren:"hasChildren"}}},[a("af-table-column",{attrs:{prop:"orgName",fixed:"left",label:"部门名称"}}),e._v(" "),a("af-table-column",{attrs:{prop:"manager",label:"部门负责人"}}),e._v(" "),a("af-table-column",{attrs:{prop:"mobile",label:"负责人手机号"}}),e._v(" "),a("af-table-column",{attrs:{prop:"orgLevel",label:"级别"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s({0:"一级",1:"二级",2:"三级"}[t.row.orgLevel])+"\n      ")]}}])}),e._v(" "),a("af-table-column",{attrs:{prop:"phone",label:"电话"}}),e._v(" "),a("af-table-column",{attrs:{prop:"fax",label:"传真"}}),e._v(" "),a("af-table-column",{attrs:{prop:"remark",label:"说明"}}),e._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"110"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-popover",{ref:"popover-"+t.row.orgID,attrs:{placement:"top",width:"200",trigger:"manual"}},[a("p",[e._v("确定删除该数据吗？")]),e._v(" "),a("div",{staticStyle:{"text-align":"right",margin:"0"}},[a("el-button",{attrs:{size:"mini",type:"text"},on:{click:function(a){e.$refs["popover-"+t.row.orgID].doClose()}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.handleDel(t.$index,t.row)}}},[e._v("确定")])],1),e._v(" "),a("el-button",{staticClass:"btn",attrs:{slot:"reference",type:"text",size:"small"},on:{click:function(a){e.$refs["popover-"+t.row.orgID].doShow()}},slot:"reference"},[e._v("删除")])],1)]}}])})],1),e._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.pageNum,limit:e.listQuery.pageSize},on:{"update:page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:limit":function(t){return e.$set(e.listQuery,"pageSize",t)},pagination:e.getList}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,"close-on-click-modal":!1,width:"50%"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"editForm",attrs:{model:e.editForm,rules:e.editFormRules,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:12}},["create"==e.dialogStatus?a("el-form-item",{staticClass:"form-item",attrs:{label:"级别:",prop:"orgLevel"}},[a("el-radio-group",{model:{value:e.editForm.orgLevel,callback:function(t){e.$set(e.editForm,"orgLevel",t)},expression:"editForm.orgLevel"}},[a("el-radio",{attrs:{label:0}},[e._v("一级")]),e._v(" "),a("el-radio",{attrs:{label:1}},[e._v("二级")]),e._v(" "),a("el-radio",{attrs:{label:2}},[e._v("三级")])],1)],1):e._e(),e._v(" "),0!=e.editForm.upOrgID&&0!=e.editForm.orgLevel?a("el-form-item",{staticClass:"form-item",attrs:{label:"上级部门:",prop:"upOrgID"}},[a("treeselect",{staticClass:"v_treeSelect",attrs:{options:e.deptOptions,normalizer:e.normalizer,placeholder:"选择上级部门"},model:{value:e.editForm.upOrgID,callback:function(t){e.$set(e.editForm,"upOrgID",t)},expression:"editForm.upOrgID"}})],1):e._e(),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"部门名称:",prop:"orgName"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"部门名称"},model:{value:e.editForm.orgName,callback:function(t){e.$set(e.editForm,"orgName",t)},expression:"editForm.orgName"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"部门负责人:",prop:"manager\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"部门负责人"},model:{value:e.editForm.manager,callback:function(t){e.$set(e.editForm,"manager",t)},expression:"editForm.manager"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"负责人手机号:",prop:"mobile\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"负责人手机号"},model:{value:e.editForm.mobile,callback:function(t){e.$set(e.editForm,"mobile",t)},expression:"editForm.mobile"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"传真:",prop:"fax"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"传真"},model:{value:e.editForm.fax,callback:function(t){e.$set(e.editForm,"fax",t)},expression:"editForm.fax"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"电话:",prop:"phone"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"电话"},model:{value:e.editForm.phone,callback:function(t){e.$set(e.editForm,"phone",t)},expression:"editForm.phone"}})],1),e._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"说明:",prop:"remark\t"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"说明",type:"textarea",autosize:{minRows:1,maxRows:4}},model:{value:e.editForm.remark,callback:function(t){e.$set(e.editForm,"remark",t)},expression:"editForm.remark"}})],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},nativeOn:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.createData}},[e._v("\n        "+e._s("create"==e.dialogStatus?"添加":"保存")+"\n      ")])],1)],1)],1)},r=[],o=a("365c"),l=a("333d"),n=a("ca17"),s=a.n(n),c=(a("542c"),{name:"index",components:{Pagination:l["a"],Treeselect:s.a},data:function(){var e=function(e,t,a){/^((1[3,5,8,7,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[1,7]))\d{8}$/.test(t)?a():a(new Error("手机号有误，请重新填写"))};return{loading:!0,list:[],total:0,listQuery:{pageNum:1,pageSize:20,manager:"",mobile:"",orgName:""},dialogStatus:"",textMap:{update:"编辑",create:"新增"},dialogFormVisible:!1,editForm:{orgLevel:"",orgName:"",chainID:"",fax:"",manager:"",mobile:"",phone:"",remark:"",upOrgID:""},editFormRules:{orgLevel:[{required:!0,message:"级别不能为空",trigger:"blur"}],upOrgID:[{required:!0,message:"上级部门不能为空",trigger:"blur"}],orgName:[{required:!0,message:"部门名称不能为空",trigger:"blur"}],mobile:[{required:!0,message:"负责人手机号不能为空",trigger:"blur"},{required:!0,validator:e,trigger:"blur"}]},deptOptions:[]}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.loading=!0,Object(o["c"])("organize/treeList",this.listQuery).then((function(t){e.loading=!1,e.list=t.data,e.deptOptions=t.data})).catch((function(t){e.loading=!1,e.list=[],e.deptOptions=[]}))},handleAdd:function(){var e=this;this.dialogStatus="create",this.dialogFormVisible=!0,this.editForm={orgLevel:0,orgName:"",chainID:"",fax:"",manager:"",mobile:"",phone:"",remark:"",upOrgID:null},this.$nextTick((function(){e.$refs["editForm"].clearValidate()}))},handleEdit:function(e,t){var a=this;if(t)for(var i in t)null!=t[i]&&"undefined"!==typeof t[i]&&void 0!=t[i]||(t[i]="");this.dialogStatus="update",this.dialogFormVisible=!0,this.editForm=Object.assign({},t),this.$nextTick((function(){a.$refs["editForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["editForm"].validate((function(t){t&&(null!=e.editForm.upOrgID&&"undefined"!==typeof e.editForm.upOrgID&&void 0!=e.editForm.upOrgID||(e.editForm.upOrgID=""),Object(o["c"])("organize/".concat("create"==e.dialogStatus?"add":"update"),e.editForm).then((function(t){200==t.code?(e.dialogFormVisible=!1,e.getList(),e.$message.success({message:"create"==e.dialogStatus?"提交成功":"保存成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)})))}))},handleDel:function(e,t){var a=this;Object(o["c"])("organize/delete/"+t.orgID).then((function(i){a.$message.success({type:"success",message:"删除成功"}),a.list.splice(e,1),a.total--,a.$refs["popover-"+t.orgID].doClose()})).catch((function(e){a.$refs["popover-"+t.orgID].doClose(),a.$message.error(e.message)}))},normalizer:function(e){return e.children&&!e.children.length&&delete e.children,{id:e.orgID,label:e.orgName,children:e.children}}}}),u=c,d=a("2877"),m=Object(d["a"])(u,i,r,!1,null,"99224fca",null);t["default"]=m.exports}}]);