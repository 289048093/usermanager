<%--
  Created by IntelliJ IDEA.
  User: Koala
  Date: 14-7-3
  Time: 下午5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>注册-喀日曲用户中心</title>
    <!--<link rel="stylesheet" href="static/css/common.css" />-->
    <link rel="stylesheet" href="<c:url value="/resources/css/account.css"/>" />

    <!--[if IE 6]>
    <script type="text/javascript" src="https://login.ejushang.com/static/js/DD_belatedPNG.js"></script>
    <script>DD_belatedPNG.fix('.ie6png');</script>
    <![endif]-->
</head>
<body>
<input name="isRedirect" type="hidden" id="isRedirect" value="false">
<!-- 注册页 -->
<div class="login-page register-page">
    <div class="login-bg">
        <div class="e-wrapper">
            <div class="login-page-main">
                <div class="head"><span>喀日曲用户中心</span></div>
                <h3>注册帐户</h3>
                <form method="POST" id="register-form" class="login-form">
                    <input type="hidden" name="service" value="${service}" />
                    <ul>
                        <li class="item-row">
                            <label for="username" class="form-label">手机号/邮箱/用户名</label>
                            <input type="text" class="form-text" name="userName" id="username" autocomplete="off"/>
                            <span class="errormsg" id="errormsg-username"></span>
                        </li>
                        <li class="item-row">
                            <label for="password" class="form-label">请输入密码</label>
                            <input type="password" name="password" class="form-text" id="password"/>
                            <span class="errormsg" id="errormsg-password"></span>
                        </li>
                        <li class="item-row">
                            <label for="rePassword" class="form-label">请再次输入密码</label>
                            <input type="password" name="rePassWord" class="form-text" id="rePassword"/>
                            <span class="errormsg" id="errormsg-rePassword"></span>
                        </li>
                        <li class="code-row">
                            <label for="code" class="form-label">输入验证码</label>
                            <input type="text" class="form-text form-code" id="code" name ="imageCode"/>
                            <a href="javascript:void(0);" class="reload-imageCode">
                                <img id="imageCode" class="imageCode" alt="" title="看不清?换一张" src="<c:url value="/imageCode" />">
                                <span>看不清，换一张</span>
                            </a>
                            <span class="errormsg" id="errormsg-code"></span>
                        </li>
                        <li class="options-row">
                            <input type="checkbox" name="rememberName" id="rememberName" checked>
                            <label for="rememberName">我已经阅读并同意<a href="javascript:void(0);" class="link link-agreement">《用户注册协议》</a></label>
                        </li>
                        <li class="btns">
                            <button type="submit" class="e-btn btn-login" id="btn-register">注册</button>
                        </li>
                        <li>已有账号？<a href="<c:url value="/login" />" class="link">直接登录</a></li>
                    </ul>
                </form>

                <div class="agreement">
                    <h4 class="t">易居尚电子商务网站用户注册协议</h4>
                    <div class="agreement-content">

                        <p>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 欢迎访问 www.ejushang.com（易居尚）。<br />
                            本协议是您与深圳易居尚网络科技有限公司（下称“易居尚”）之间就易居尚网站服务等相关事宜所订立的契约，请您仔细阅读本注册协议，您点击“同意以上协议”按钮后，本协议即构成对双方有约束力的法律文件。
                        </p>

                        <h5>一，服务条款确认和接纳</h5>
                        <p>
                            1.1 本站的各项电子服务的所有权和运作权归易居尚所有，用户同意所有注册协议条款 并完成注册程序，才能成为本站的正式用户。<br />
                            1.2 用户点击同意本协议的，即视为确认自己具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。<br />
                            1.3 若会员需要在易居尚行使自身行为能力的权利，应当在监护人的监护参与下才能使用相关服务。<br />
                            1.4 注册会员在使用易居尚提供的各项服务的同时，承诺接受并遵守各项相关条款的规定。
                        </p>

                        <h5>二，用户的权利与义务</h5>
                        <p>
                            2.1 用户一旦注册成功，即成为易居尚的合法注册会员，有权享受易居尚提供的各项服务。<br />
                            2.2 用户应妥善保管已注册的会员账号信息，对注册会员名和密码安全承担全部责任，会员可随时根据需要改变会员密码。<br />
                            2.3 为更好的接受易居尚提供的产品和服务，会员应当提供详尽、准确的个人资料，并不时维护更新，符合及时、详尽、准确的要求。<br />
                            2.4 会员不得传输或发表：煽动抗拒，破坏宪法和法律，行政法规实施的言论，民族歧视，破坏民族团结的言论。<br />
                            2.5 未经易居尚同意，禁止用户在网站发布任何形式的广告<br />
                            2.6 会员不得利用本站从事洗钱，窃取商业机密，窃取个人信息等违法犯罪活动。<br />
                            2.7 会员不得干扰本站的正常运转，不得侵入本站及国家计算机信息系统。<br />
                            2.8 会员不得传输或发表任何违法犯罪的，骚扰性的，庸俗性的，不文明的等信息资料。
                        </p>

                        <h5>三，易居尚的权利与义务</h5>
                        <p>
                            3.1 为会员提供网上购物平台，提供便利优质的购物环境和售前、售中、售后服务。<br />
                            3.2 保障和维护全体注册会员利益，并承诺做到保质量，保价格，保服务。<br />
                            3.3 对注册会员的电子邮件、手机号等隐私资料进行保护。<br />
                            3.4 对注册会员的电子邮件、手机号等隐私资料进行保护，承诺不会在未获得注册会员许可的情况下擅自将注册会员的个人资料信息出租或出售给任何第三方，但以下情况除外：<br />
                            3.4.1 会员同意让第三方共享资料；<br />
                            3.4.2 会员同意公开其个人资料，享受为其提供的产品和服务；<br />
                            3.3.3 本站需要听从法庭传票、法律命令或遵循法律程序；<br />
                            3.3.4 本站发现注册会员违反了本站服务条款或本站其它使用规定；<br />
                            3.5 易居尚应尽最大努力保证您所购商品与本站公布的价格一致，但价目表和声明并不构成要约。易居尚有权在发现了网站上显现的产品信息及订单的明显错误的情况下，单方面撤回任何承诺。同时，本站保留对产品订购的数量的限制权。<br />
                            3.6 定期通过页面公告及电子邮件方式向注册会员发送活动信息。<br />
                            3.7 通过页面的公告向注册会员发布协议条款的修改、服务变更、或其它重要事件。
                        </p>

                        <h5>四，订单确认</h5>
                        <p>
                            4.1 在您下订单时，请您仔细确认所购商品的名称、价格、数量、型号、规格、尺寸、联系地址、电话、收货人等信息。收货人与用户本人不一致的，收货人的行为和意思表示视为用户的行为和意思表示，用户应对收货人的行为及意思表示的法律后果承担连带责任。<br />
                            4.2 由于市场变化及各种以合理商业努力难以控制的因素的影响，易居尚无法保证您提交的订单信息中希望购买的商品都会有货；如您拟购买的商品，发生缺货，您有权取消订单。
                        </p>

                        <h5>五，订单的终止和解除</h5>
                        <p>
                            5.1 用户有权在下列情况下，取消订单：<br />
                            5.1.1 经用户和易居尚协商达成一致的；<br />
                            5.1.2 易居尚就用户订单做出承诺之前；<br />
                            5.1.3 易居尚网站上的公布的商品价格发生变化或错误，用户在易居尚发货之前通知易居尚的。<br />
                            5.2 易居尚在下列情况下，可以取消用户订单：<br />
                            5.2.1 经用户和易居尚协商达成一致的；<br />
                            5.2.2 易居尚网站上显示的商品信息明显错误的；<br />
                            5.2.3 用户订单信息明显错误或超出易居尚存货量；<br />
                            5.2.4 经易居尚判断不符合公平和诚实信用原则的情形，如同一用户多次无理由拒收商品。
                        </p>

                        <h5>六，配送</h5>
                        <p>
                            6.1 商家将会委托第三方快递服务商把货物送到您所指定的收货地址，所有在易居尚列出的送货时间为参考时间，参考时间的计算是根据库存状况、正常的处理过程和送货时间、送货地点的基础上计算得出。<br />
                            6.2 因如下情况造成的订单延迟或无法配送等，易居尚不承担延迟配送的责任：<br />
                            6.2.1 用户提供的信息错误，地址不详细等原因导致的；<br />
                            6.2.2 货物送达后无人签收，导致无法配送或延迟配送；<br />
                            6.2.3 收货信息变更因素导致的；<br />
                            6.2.4 不可抗力因素导致的，例如：自然灾害，交通戒严，突发战争等。
                        </p>

                        <h5>七，退款政策</h5>
                        <p>
                            7.1 退货或换货商品缺货时产生的现金款项，退回方式视支付方式的不同而不同：<br />
                            7.1.1 网上支付或银行汇款的订单，退款退回至原支付卡。<br />
                            7.1.2 货到付款或邮局汇款支付的订单，退款退回至您提供的账户中。<br />
                            7.2 用户希望购买的商品如果发生缺货，用户和易居尚皆有权取消该订单。<br />
                            7.3 易居尚可对缺货商品进行预售登记，易居尚会尽最大努力在最快时间内满足用户的购买需求，当缺货商品到货，易居尚将第一时间通过邮件、短信或电话通知用户，方便用户进行购买。预售登记并不做订单处理，不构成要约。
                        </p>

                        <h5>八，服务的中断和终止：</h5>
                        <p>
                            8.1 如用户向易居尚提出注销用户身份时，经易居尚审核同意，由易居尚注销该用户，用户即解除与易居尚的服务协议关系。但注销该用户账号后，易居尚仍保留下列权利：<br />
                            8.1.1 易居尚有权保留该用户的注册信息及以前的交易行为记录；<br />
                            8.1.2 如用户在注销前在易居尚交易平台上存在违法行为或违反本协议的行为，易居尚仍可行使本服务协议所规定的权利。<br />
                            8.2 在下列情况下，易居尚可以通过注销用户的方式终止服务：<br />
                            8.2.1 在用户违反本服务协议相关规定时，易居尚有权终止向该用户提供服务。但如该用户在易居尚终止提供服务后，再一次直接或间接或以他人名义注册为易居尚用户的，易居尚有权再次单方面终止向该用户提供服务；<br />
                            8.2.2 如易居尚通过用户提供的信息与用户联系时，发现用户在注册时填写的电子邮箱已不存在或无法接收电子邮件的，经易居尚以其它联系方式通知用户更改，而用户在三个工作日内仍未能提供新的电子邮箱地址的，易居尚有权终止向该用户提供服务；<br />
                            8.2.3 一经易居尚发现用户注册信息中主要内容是虚假的，易居尚有权随时终止向该用户提供服务；<br />
                            8.2.4 本服务协议终止或更新时，用户明示不愿接受新的服务协议的；<br />
                            8.2.5 其它易居尚认为需终止服务的情况。
                        </p>

                        <h5>九，所有权及知识产权</h5>
                        <p>
                            9.1 用户一旦接受本协议，即表明该用户主动将其在任何时间段在易居尚网站上发表的任何形式的信息内容（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，全部独家且不可撤销地转让给易居尚所有，用户同意易居尚有权就任何主体侵权而单独提起诉讼。<br />
                            9.2 用户同意并已充分了解本协议的条款，承诺不将已发表于本站的信息，以任何形式发布或授权其它主体以任何方式使用（包括但限于在各类网站、媒体上使用）。<br />
                            9.3 易居尚是本站的制作者，拥有此网站内容及资源的著作权等合法权利，受国家法律保护，有权不时地对本协议及本站的内容进行修改，并在本站张贴，无须另行通知用户。在法律允许的最大限度范围内，易居尚对本协议及本站内容拥有解释权。<br />
                            9.4 除法律另有强制性规定外，未经易居尚明确的特别书面许可,任何单位或个人不得以任何方式非法地全部或部分复制、转载、引用、链接、抓取或以其他方式使用本站的信息内容，否则，易居尚有权追究其法律责任。<br />
                            9.5 本站所刊登的资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均是易居尚或其内容提供者的财产，受中国和国际版权法的保护。本站上所有内容的汇编是易居尚的排他财产，受中国和国际版权法的保护。本站上所有软件都是易居尚或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。
                        </p>

                        <h5>十，法律管辖和适用</h5>
                        <p>
                            十，法律管辖和适用<br />
                            本协议的订立、执行和解释及争议的解决均应适用在中华人民共和国大陆地区适用之有效法律（但不包括其冲突法规则）。 如发生本协议与适用之法律相抵触时，则这些条款将完全按法律规定重新解释，而其它有效条款继续有效。 如缔约方就本协议内容或其执行发生任何争议，双方应尽力友好协商解决；协商不成时，任何一方均可向有管辖权的中华人民共和国大陆地区法院提起诉讼。
                        </p>

                        <h5>十一，其他</h5>
                        <p>
                            11.1 本服务条款以及与使用"本站"服务相关的声明、说明、规则、政策、程序，构成"本站"与用户之间有效的完整协议，将对用户持续有效，有效期至用户注销或者被取消账户、且停止一切使用"本站"服务的行为后，但"本站"与用户间已经产生的权利义务，仍对双方有约束力。<br />
                            11.2本服务条款中的任何条款无论因何种原因完全或部分无效或不具有执行力，其余条款仍应有效并且有约束力。<br />
                            11.3 本服务条款的标题、序号均为方便阅读而设定，不影响条款实际含义及其效力。<br />
                            11.4 本服务条款自公布及适用的当天生效，有效期持续至更新版本发布之时。<br />
                            11.5 以上条款的解释权归深圳市易居尚电子商务有限公司最终所有。
                        </p>

                        <p>
                            <strong>十二，</strong>使用QQ号、微博账号等第三方关联登录易居尚网站的用户，视同注册会员，享有注册会员的同等权利和义务。
                        </p>
                    </div>
                    <a href="javascript:void(0);" class="btn-close">关闭</a>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /注册页 -->
<!-- 网站底部 -->
<div class="e-footer" id="e-footer">
    <div class="e-wrapper">
        <p class="text-center">&copy; 2014 ejushang.com 版权所有 | <a href="http://www.miibeian.gov.cn/" target="_blank">粤ICP备12069686号-1</a></p>
        <p class="text-center"><a href="http://www.ejushang.com/about/33.html">关于我们</a>｜客服热线 400-9933-178</p>
    </div>
</div>
<!-- 网站底部 END -->
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/user/FormValidate.js"/>"></script>
<script src="<c:url value="/resources/js/user/login.js"/>"></script>
<script type="text/javascript">
    Ejs.user.register();
</script>
</body>
</html>
