尽管jsp已经存在了很长的时间，并且在Java Web服务器中无处不在，但是它却存在一些缺陷。
Jsp最明显的问题在于他看起来像HTML或XML，但它其实上并不是。大多数的JSP模板都采用HTML
的形式，但是又掺杂上了各种JSP标签库的标签，使其变得很混乱。这些标签能够以很便利的方式
为JSP带来动态渲染的强大功能，但是它也摧毁了我们想维持一个格式良好的文档的可能性。

JSP缺乏良好格式的一个副作用就是它很少能够与其产生的HTML类似。
所以在web浏览器或HTML编辑器中查看未经渲染的JSP模板是非常令人困惑的，
而且得到的结果看上去也非常的丑陋。

同时JSP规范与Servlet规范是紧密耦合的。这意味着它只能用在基于Servlet的Web应用中。
JSP模板不能作为通用的模板，也不能用于非Servlet的Web应用。

Thymeleaf作为JSP在视图领域的统治性地位的挑战者，优点是，Thymeleaf模板是原生的，不依赖于标签库。
它能在接受原始HTML的地方进行标记和渲染。因为它没有与Servlet规范耦合，因此Thymeleaf模板能够进入
JSP所无法涉足的领域（技术辐射范围广）