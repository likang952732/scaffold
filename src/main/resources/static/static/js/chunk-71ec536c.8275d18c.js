(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-71ec536c"],{"11e9":function(t,e,a){var n=a("52a7"),r=a("4630"),o=a("6821"),s=a("6a99"),i=a("69a8"),l=a("c69a"),c=Object.getOwnPropertyDescriptor;e.f=a("9e1e")?c:function(t,e){if(t=o(t),e=s(e,!0),l)try{return c(t,e)}catch(a){}if(i(t,e))return r(!n.f.call(t,e),t[e])}},"333d":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,"page-sizes":t.pageSizes,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"update:page-size":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},r=[];a("c5f6");Math.easeInOutQuad=function(t,e,a,n){return t/=n/2,t<1?a/2*t*t+e:(t--,-a/2*(t*(t-2)-1)+e)};var o=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}();function s(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}function i(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(t,e,a){var n=i(),r=t-n,l=20,c=0;e="undefined"===typeof e?500:e;var u=function t(){c+=l;var i=Math.easeInOutQuad(c,n,r,e);s(i),c<e?o(t):a&&"function"===typeof a&&a()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&l(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},u=c,p=(a("e498"),a("2877")),f=Object(p["a"])(u,n,r,!1,null,"6af373ef",null);e["a"]=f.exports},"5dbc":function(t,e,a){var n=a("d3f4"),r=a("8b97").set;t.exports=function(t,e,a){var o,s=e.constructor;return s!==a&&"function"==typeof s&&(o=s.prototype)!==a.prototype&&n(o)&&r&&r(t,o),t}},7456:function(t,e,a){},"8b97":function(t,e,a){var n=a("d3f4"),r=a("cb7c"),o=function(t,e){if(r(t),!n(e)&&null!==e)throw TypeError(e+": can't set as prototype!")};t.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,e,n){try{n=a("9b43")(Function.call,a("11e9").f(Object.prototype,"__proto__").set,2),n(t,[]),e=!(t instanceof Array)}catch(r){e=!0}return function(t,a){return o(t,a),e?t.__proto__=a:n(t,a),t}}({},!1):void 0),check:o}},9093:function(t,e,a){var n=a("ce10"),r=a("e11e").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return n(t,r)}},"9f54":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0,model:t.listQuery,rules:t.listQueryRules},nativeOn:{submit:function(t){t.preventDefault()}}},[a("el-form-item",{staticClass:"form-item",attrs:{label:"资产编号:"}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"资产编号"},model:{value:t.listQuery.assetNo,callback:function(e){t.$set(t.listQuery,"assetNo",e)},expression:"listQuery.assetNo"}})],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"部门名称:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",filterable:"","popper-append-to-body":!1},on:{change:t.orgChange},model:{value:t.listQuery.orgID,callback:function(e){t.$set(t.listQuery,"orgID",e)},expression:"listQuery.orgID"}},t._l(t.orgOptions,(function(t){return a("el-option",{key:t.orgID,attrs:{label:t.orgName,value:t.orgID}})})),1)],1),t._v(" "),t.listQuery.orgID?a("el-form-item",{staticClass:"form-item",attrs:{label:"部门负责人:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",filterable:"","popper-append-to-body":!1},model:{value:t.listQuery.staff,callback:function(e){t.$set(t.listQuery,"staff",e)},expression:"listQuery.staff"}},t._l(t.staffOptions,(function(t){return a("el-option",{key:t.fullName,attrs:{label:t.fullName,value:t.fullName}})})),1)],1):t._e(),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"资产名称:",prop:"assetID"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",filterable:"","popper-append-to-body":!1},model:{value:t.listQuery.assetID,callback:function(e){t.$set(t.listQuery,"assetID",e)},expression:"listQuery.assetID"}},t._l(t.assetOptions,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"form-item",attrs:{label:"排序方式:"}},[a("el-select",{staticClass:"form-select",staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"","popper-append-to-body":!1},model:{value:t.listQuery.sortType,callback:function(e){t.$set(t.listQuery,"sortType",e)},expression:"listQuery.sortType"}},[a("el-option",{attrs:{value:"0",label:"时间倒序"}}),t._v(" "),a("el-option",{attrs:{value:"1",label:"时间正序"}})],1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"success",size:"mini",icon:"el-icon-search"},on:{click:t.getList}},[t._v("查询")])],1)],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"table",staticStyle:{width:"100%"},attrs:{data:t.list,border:"","header-cell-style":{background:"#F8F8F9",fontSize:"13px",color:"#515a6e"}}},[a("af-table-column",{attrs:{prop:"assetName",fixed:"left",label:"资产名称"}}),t._v(" "),a("af-table-column",{attrs:{prop:"assetNo",label:"资产编号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"alarmType",label:"报警类型","min-width":"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({1:"异常外出",2:"进出状态不匹配",4:"黑名单",5:"销毁资产"}[e.row.alarmType])+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"posName",label:"位置名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"isAlarm",label:"是否报警"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"不报警",1:"报警"}[e.row.isAlarm])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"directory",label:"进出方向",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"进",1:"出"}[e.row.directory])+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"preStatus",label:"原状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"内部",1:"外部"}[e.row.preStatus])+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"statusMatched",label:"原状态是否正常","min-width":"140"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s({0:"正常",1:"原状态不匹配"}[e.row.preStatus])+"\n      ")]}}])}),t._v(" "),a("af-table-column",{attrs:{prop:"statusTimes",label:"原状态保持时间(小时)"}}),t._v(" "),a("af-table-column",{attrs:{prop:"timeAdd",label:"进出时间"}}),t._v(" "),a("af-table-column",{attrs:{prop:"timePreStatus",label:"原状态时间"}})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.pageNum,limit:t.listQuery.pageSize},on:{"update:page":function(e){return t.$set(t.listQuery,"pageNum",e)},"update:limit":function(e){return t.$set(t.listQuery,"pageSize",e)},pagination:t.getList}}),t._v(" "),0==t.fistFlag?a("amend-pwd"):t._e()],1)},r=[],o=a("5530"),s=a("365c"),i=a("333d"),l=a("2f62"),c={name:"trajectory",components:{Pagination:i["a"]},computed:Object(o["a"])({},Object(l["b"])(["name","fistFlag","userForm"])),data:function(){return{listQuery:{pageNum:1,pageSize:20,sortType:"",assetID:"",orgID:"",staff:"",assetNo:""},listQueryRules:{},list:[],loading:!1,total:0,assetOptions:[],orgOptions:[],staffOptions:[],assetIds:""}},created:function(){this.assetGetList(),this.orgGetList(),this.getList()},methods:{getList:function(){var t,e=this;t=null==this.$route.query.id?this.listQuery.assetID:this.$route.query.id,this.listQuery=Object(o["a"])(Object(o["a"])({},this.listQuery),{},{assetID:t}),this.loading=!0,Object(s["c"])("assetrecord/page",this.listQuery).then((function(t){e.loading=!1,e.list=t.data.list,e.total=t.data.total})).catch((function(t){e.loading=!1,e.list=[],e.total=0}))},orgGetList:function(){var t=this;Object(s["c"])("user/getOrg").then((function(e){t.orgOptions=e.data})).catch((function(e){t.orgOptions=[]}))},orgChange:function(t){this.assetGetList(),this.getStaffList(t)},getStaffList:function(t){var e=this;Object(s["c"])("personnellib/list",{orgID:t}).then((function(t){e.staffOptions=t.data})).catch((function(t){e.staffOptions=[]}))},assetGetList:function(){var t=this,e={orgID:this.listQuery.orgID};Object(s["c"])("asset/list",e).then((function(e){t.assetOptions=e.data})).catch((function(e){t.assetOptions=[]}))}}},u=c,p=a("2877"),f=Object(p["a"])(u,n,r,!1,null,"bb5ca57a",null);e["default"]=f.exports},aa77:function(t,e,a){var n=a("5ca1"),r=a("be13"),o=a("79e5"),s=a("fdef"),i="["+s+"]",l="​",c=RegExp("^"+i+i+"*"),u=RegExp(i+i+"*$"),p=function(t,e,a){var r={},i=o((function(){return!!s[t]()||l[t]()!=l})),c=r[t]=i?e(f):s[t];a&&(r[a]=c),n(n.P+n.F*i,"String",r)},f=p.trim=function(t,e){return t=String(r(t)),1&e&&(t=t.replace(c,"")),2&e&&(t=t.replace(u,"")),t};t.exports=p},c5f6:function(t,e,a){"use strict";var n=a("7726"),r=a("69a8"),o=a("2d95"),s=a("5dbc"),i=a("6a99"),l=a("79e5"),c=a("9093").f,u=a("11e9").f,p=a("86cc").f,f=a("aa77").trim,d="Number",m=n[d],g=m,b=m.prototype,y=o(a("2aeb")(b))==d,h="trim"in String.prototype,v=function(t){var e=i(t,!1);if("string"==typeof e&&e.length>2){e=h?e.trim():f(e,3);var a,n,r,o=e.charCodeAt(0);if(43===o||45===o){if(a=e.charCodeAt(2),88===a||120===a)return NaN}else if(48===o){switch(e.charCodeAt(1)){case 66:case 98:n=2,r=49;break;case 79:case 111:n=8,r=55;break;default:return+e}for(var s,l=e.slice(2),c=0,u=l.length;c<u;c++)if(s=l.charCodeAt(c),s<48||s>r)return NaN;return parseInt(l,n)}}return+e};if(!m(" 0o1")||!m("0b1")||m("+0x1")){m=function(t){var e=arguments.length<1?0:t,a=this;return a instanceof m&&(y?l((function(){b.valueOf.call(a)})):o(a)!=d)?s(new g(v(e)),a,m):v(e)};for(var _,S=a("9e1e")?c(g):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),N=0;S.length>N;N++)r(g,_=S[N])&&!r(m,_)&&p(m,_,u(g,_));m.prototype=b,b.constructor=m,a("2aba")(n,d,m)}},e498:function(t,e,a){"use strict";a("7456")},fdef:function(t,e){t.exports="\t\n\v\f\r   ᠎             　\u2028\u2029\ufeff"}}]);