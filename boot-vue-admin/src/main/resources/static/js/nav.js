$("head").append(`

<style>
    .subNavBox{width:100%; border:solid 1px #e5e3da; min-height: calc(100vh - 70px)}
    .subNav{border-bottom:solid 1px #e5e3da;cursor:pointer;font-weight:bold;font-size:14px;color:#999;
        line-height:35px;padding-left:10px; background:url(../img/jiantou1.jpg) no-repeat;background-position:95% 50%}
    .subNav:hover{color:#277fc2;}
    .currentDd{color:#277fc2}
    .currentDt{background-image:url(../img/jiantou.jpg);}
    .navContent{display: none;border-bottom:solid 1px #e5e3da;}
    .navContent li a{display:block;height:35px;text-align:center;font-size:14px;line-height:35px;color:#333}
    .navContent li a:hover{color:#fff !important; background-color:#277fc2}
    .navActive { color:  #277fc2 !important;}
</style>

`)

document.write(`
    
<!-- 侧边栏 -->
<div style="width: 220px;">
  <div class="subNavBox">
      <div class="subNav currentDt currentDd">主页</div>
      <ul class="navContent" style="display:block">
          <li><a href="/index.html" class="navActive">主页</a></li>
      </ul>
      <div class="subNav currentDt">系统管理</div>
      <ul class="navContent" style="display:block">
          <li><a href="/user.html">用户管理</a></li>
      </ul>
  </div>
</div>

<script>
    $(function(){
        $(".subNav").click(function(){
            $(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd");
            $(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt");
            $(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(500);
        })
        
        let uri = location.href.substring(location.href.lastIndexOf("/"))
        if (uri === '/') {
            uri = '/index.html'
        }
        
        $(".subNav").removeClass("currentDd")
        $(".navContent").find("a").removeClass("navActive")
        let $a = $(".navContent").find("a")
        $.each($a, function(index,ele) {
            if ($(ele).attr('href').includes(uri)) {  // 高亮
                $(ele).addClass("navActive") // 加一个高亮的class
                
                // 获取到当前菜单父级的兄弟节点 div，给他高亮
                $(ele).parent("li").parent("ul").prev().addClass("currentDd")
            }
        })
    })
</script>

`)
