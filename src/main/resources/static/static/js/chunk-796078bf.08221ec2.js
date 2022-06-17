(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-796078bf"],{"037d":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:t.listQuery,"label-width":"100px"},nativeOn:{submit:function(t){t.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"资产编号:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产编号"},model:{value:t.listQuery.assetNo,callback:function(e){t.$set(t.listQuery,"assetNo",e)},expression:"listQuery.assetNo"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"销毁状态:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.delStatus,callback:function(e){t.$set(t.listQuery,"delStatus",e)},expression:"listQuery.delStatus"}},[a("el-option",{attrs:{value:0,label:"未销毁"}}),t._v(" "),a("el-option",{attrs:{value:1,label:"已销毁"}})],1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"所属部门:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},on:{change:t.orgChange},model:{value:t.listQuery.orgID,callback:function(e){t.$set(t.listQuery,"orgID",e)},expression:"listQuery.orgID"}},t._l(t.orgOptions,(function(t){return a("el-option",{key:t.orgID,attrs:{label:t.orgName,value:t.orgID}})})),1)],1),t._v(" "),t.listQuery.orgID?a("el-form-item",{staticClass:"form-item",attrs:{label:"负责人:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",filterable:"","popper-append-to-body":!1},model:{value:t.listQuery.staff,callback:function(e){t.$set(t.listQuery,"staff",e)},expression:"listQuery.staff"}},t._l(t.staffOptions,(function(t){return a("el-option",{key:t.fullName,attrs:{label:t.fullName,value:t.fullName}})})),1)],1):t._e(),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"资产名称:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","value-key":"id","popper-append-to-body":!1},on:{change:t.assetChange},model:{value:t.listQuery.name,callback:function(e){t.$set(t.listQuery,"name",e)},expression:"listQuery.name"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item"},[a("el-button",{attrs:{size:"mini",icon:"el-icon-refresh"},on:{click:t.colse}},[t._v("重置")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:t.getList}},[t._v("查询")])],1)],1)],1),t._v(" "),a("div",{staticClass:"table_content"},[a("div",{staticClass:"btn-btm"},[a("el-button",{staticClass:"btn-add",attrs:{type:"primary",plain:"",size:"mini",disabled:0==t.selection.length},on:{click:t.destruction}},[t._v("批量销毁资产")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,"header-cell-style":{background:"#EBEBEB",padding:"6px 0 !important",color:"#515a6e"},"tooltip-effect":"dark"},on:{"selection-change":t.handleSelectionChange}},[a("af-table-column",{attrs:{fixed:"left",type:"selection",selectable:t.checkSelectable}}),t._v(" "),a("af-table-column",{attrs:{prop:"assetNo",fixed:"left",label:"资产编号",width:"150"}}),t._v(" "),a("af-table-column",{attrs:{prop:"name",label:"资产名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"orgName",label:"所属部门"}}),t._v(" "),a("af-table-column",{attrs:{prop:"delStatus",label:"销毁状态",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n          "+t._s({0:"未销毁",1:"已销毁"}[e.row.delStatus])+"\n        ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"delReason",label:"销毁原因"}}),t._v(" "),a("el-table-column",{attrs:{prop:"delTime",label:"销毁时间","min-width":"150"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"right",label:"操作","min-width":"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[0==e.row.delStatus?a("el-button",{staticClass:"btn",attrs:{type:"text",size:"small"},on:{click:function(a){return t.handleEdit(e.row)}}},[t._v("销毁资产")]):t._e()]}}])})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.pageNum,limit:t.listQuery.pageSize},on:{"update:page":function(e){return t.$set(t.listQuery,"pageNum",e)},"update:limit":function(e){return t.$set(t.listQuery,"pageSize",e)},pagination:t.getList}})],1),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},i=[],s=(a("7f7f"),a("b85c")),o=a("5530"),r=a("365c"),l=a("2f62"),c=a("333d"),u={name:"destruction",components:{Pagination:c["a"]},computed:Object(o["a"])({},Object(l["b"])(["sidebar","avatar","name","perms","fistFlag","userForm"])),data:function(){return{listQuery:{pageNum:1,pageSize:20,delStatus:0,assetNo:"",orgID:"",name:"",assetID:"",staff:""},list:[],total:0,loading:!1,selection:[],multipleSelection:[],assetOptions:[],orgOptions:[],staffOptions:[]}},created:function(){this.getList(),this.assetGetList(),this.orgGetList()},methods:{colse:function(){this.listQuery={pageNum:1,pageSize:20,delStatus:0,assetNo:"",orgID:"",name:"",assetID:"",staff:""}},getList:function(){var t=this;this.loading=!0,Object(r["c"])("asset/page",this.listQuery).then((function(e){t.loading=!1,t.list=e.data.list,t.total=e.data.total})).catch((function(e){t.loading=!1,t.list=[],t.total=0}))},handleSelectionChange:function(t){this.selection=[],this.multipleSelection=t;var e,a=Object(s["a"])(this.multipleSelection);try{for(a.s();!(e=a.n()).done;){var n=e.value;this.selection.push(n.id)}}catch(i){a.e(i)}finally{a.f()}},checkSelectable:function(t){return 0==t.delStatus},assetGetList:function(){var t=this;Object(r["c"])("asset/list",{orgID:this.listQuery.orgID}).then((function(e){t.assetOptions=e.data})).catch((function(e){t.assetOptions=[]}))},destruction:function(){var t=this;this.$prompt("销毁原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputPlaceholder:"请输入销毁原因",showInput:!0,inputValidator:function(t){if(null==t||""==t)return!1},inputErrorMessage:"销毁原因不能为空"}).then((function(e){var a=e.value;null!=a&&"undefined"!==typeof a&&void 0!=a||(a="");var n={ids:t.selection.join(","),delReason:a};Object(r["c"])("asset/destroy",n).then((function(e){200==e.code?(t.getList(),t.$message.success({message:"提交成功",type:"success"})):t.$message.error(e.message)})).catch((function(e){t.$message.error(e.message)}))})).catch((function(){}))},handleEdit:function(t){var e=this;this.$prompt("销毁原因","提示",{confirmButtonText:"确定",cancelButtonText:"取消",inputPlaceholder:"请输入销毁原因",showInput:!0,inputValidator:function(t){if(null==t||""==t)return!1},inputErrorMessage:"销毁原因不能为空"}).then((function(a){var n=a.value;null!=n&&"undefined"!==typeof n&&void 0!=n||(n="");var i={ids:t.id,delReason:n};Object(r["c"])("asset/destroy",i).then((function(t){200==t.code?(e.getList(),e.$message.success({message:"提交成功",type:"success"})):e.$message.error(t.message)})).catch((function(t){e.$message.error(t.message)}))})).catch((function(){}))},assetChange:function(t){var e=this;this.$nextTick((function(){null==t.name||"undefined"===t.name||void 0==t.name?(e.listQuery.name="",e.listQuery.assetID=""):(e.listQuery.name=t.name,e.listQuery.assetID=t.id)}))},orgGetList:function(){var t=this;Object(r["c"])("user/getOrg").then((function(e){t.orgOptions=e.data})).catch((function(e){t.orgOptions=[]}))},orgChange:function(t){this.assetGetList(),this.getStaffList(t)},getStaffList:function(t){var e=this;Object(r["c"])("personnellib/list",{orgID:t}).then((function(t){e.staffOptions=t.data})).catch((function(t){e.staffOptions=[]}))}}},f=u,p=a("2877"),d=Object(p["a"])(f,n,i,!1,null,"449589a6",null);e["default"]=d.exports},"11e9":function(t,e,a){var n=a("52a7"),i=a("4630"),s=a("6821"),o=a("6a99"),r=a("69a8"),l=a("c69a"),c=Object.getOwnPropertyDescriptor;e.f=a("9e1e")?c:function(t,e){if(t=s(t),e=o(e,!0),l)try{return c(t,e)}catch(a){}if(r(t,e))return i(!n.f.call(t,e),t[e])}},"333d":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},i=[];a("c5f6");Math.easeInOutQuad=function(t,e,a,n){return t/=n/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var s=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function o(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function r(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,a){var n=r(),i=t-n,l=20,c=0;e="undefined"===typeof e?500:e;var u=function t(){c+=l;var r=Math.easeInOutQuad(c,n,i,e);o(r),c<e?s(t):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},u=c,f=(a("fdfd"),a("2877")),p=Object(f["a"])(u,n,i,!1,null,"d2983402",null);e["a"]=p.exports},"5dbc":function(t,e,a){var n=a("d3f4"),i=a("8b97").set;t.exports=function(t,e,a){var s,o=e.constructor;return o!==a&&"function"==typeof o&&(s=o.prototype)!==a.prototype&&n(s)&&i&&i(t,s),t}},6078:function(t,e,a){},"8b97":function(t,e,a){var n=a("d3f4"),i=a("cb7c"),s=function(t,e){if(i(t),!n(e)&&null!==e)throw TypeError(e+": can't set as prototype!")};t.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,e,n){try{n=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),n(t,[]),e=!(t instanceof Array)}catch(i){e=!0}return function(t,a){return s(t,a),e?t.__proto__=a:n(t,a),t}}({},!1):void 0),check:s}},9093:function(t,e,a){var n=a("ce10"),i=a("e11e").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return n(t,i)}},aa77:function(t,e,a){var n=a("5ca1"),i=a("be13"),s=a("79e5"),o=a("fdef"),r="["+o+"]",l="​",c=RegExp("^"+r+r+"*"),u=RegExp(r+r+"*$"),f=function(t,e,a){var i={},r=s((function(){return!!o[t]()||l[t]()!=l})),c=i[t]=r?e(p):o[t];a&&(i[a]=c),n(n.P+n.F*r,"String",i)},p=f.trim=function(t,e){return t=String(i(t)),1&e&&(t=t.replace(c,"")),2&e&&(t=t.replace(u,"")),t};t.exports=f},c5f6:function(t,e,a){"use strict";var n=a("7726"),i=a("69a8"),s=a("2d95"),o=a("5dbc"),r=a("6a99"),l=a("79e5"),c=a("9093").f,u=a("11e9").f,f=a("86cc").f,p=a("aa77").trim,d="Number",m=n[d],g=m,h=m.prototype,b=s(a("2aeb")(h))==d,y="trim"in String.prototype,v=function(t){var e=r(t,!1);if("string"==typeof e&&e.length>2){e=y?e.trim():p(e,3);var a,n,i,s=e.charCodeAt(0);if(43===s||45===s){if(a=e.charCodeAt(2),88===a||120===a)return NaN}else if(48===s){switch(e.charCodeAt(1)){case 66:case 98:n=2,i=49;break;case 79:case 111:n=8,i=55;break;default:return+e}for(var o,l=e.slice(2),c=0,u=l.length;c<u;c++)if(o=l.charCodeAt(c),o<48||o>i)return NaN;return parseInt(l,n)}}return+e};if(!m(" 0o1")||!m("0b1")||m("+0x1")){m=function(t){var e=arguments.length<1?0:t,a=this;return a instanceof m&&(b?l((function(){h.valueOf.call(a)})):s(a)!=d)?o(new g(v(e)),a,m):v(e)};for(var _,S=a("9e1e")?c(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),N=0;S.length>N;N++)i(g,_=S[N])&&!i(m,_)&&f(m,_,u(g,_));m.prototype=h,h.constructor=m,a("2aba")(n,d,m)}},fdef:function(t,e){t.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"},fdfd:function(t,e,a){"use strict";a("6078")}}]);