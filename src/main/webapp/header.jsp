<%@ page pageEncoding="UTF-8"%>
<div><img class=img1 src="img/noname.png"></div>
<header>

<form action="menu" method="get" class="headerRight">
<a href="menu?button=header">メニュー画面</a>
<a href="login?button=logout">ログアウト</a>
</form>
</header>
<style type =text/css>
.img1 {
    border-radius: 50%;  /* 角丸半径を50%にする(=円形にする) */
    width:  90px;       /* ※縦横を同値に */
    height: 90px; 
    top: 0px;
  }
div {
    text-align:left;
}
header {
  height:70px;
  position: absolute;
  width: 100%;
  z-index:1; 
  text-align:right;
  z-index: 9999;
  top: 0px;
  left: 0px;
}

.headerRight {
    text-align:right;
}
a {
  display: inline-block;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  color: #007f00; /* 文字色を濃い緑色に設定 */
  background-color: #fff; /* 背景色を白色に設定 */
  border: 2px solid #007f00; /* ボーダーを2px幅の濃い緑色の実線に設定 */
  border-radius: 0.25rem;
  transition: background-color 0.3s ease-out;
}

a:hover {
  background-color: #0056b3;
}
</style>