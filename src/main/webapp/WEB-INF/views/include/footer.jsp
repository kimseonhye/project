<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/resources/css/include/footer.css' />"
	rel="stylesheet" type="text/css">

<div id="footer_area">
	<footer>
		<div id="footer_wrap">
			<div class="office_info">KH정보교육원 R반 신입이조</div>
		</div>
	</footer>
</div>
<script>
	(function() {
		var w = window;
		if (w.ChannelIO) {
			return w.console.error("ChannelIO script included twice.");
		}
		var ch = function() {
			ch.c(arguments);
		};
		ch.q = [];
		ch.c = function(args) {
			ch.q.push(args);
		};
		w.ChannelIO = ch;
		function l() {
			if (w.ChannelIOInitialized) {
				return;
			}
			w.ChannelIOInitialized = true;
			var s = document.createElement("script");
			s.type = "text/javascript";
			s.async = true;
			s.src = "https://cdn.channel.io/plugin/ch-plugin-web.js";
			var x = document.getElementsByTagName("script")[0];
			if (x.parentNode) {
				x.parentNode.insertBefore(s, x);
			}
		}
		if (document.readyState === "complete") {
			l();
		} else {
			w.addEventListener("DOMContentLoaded", l);
			w.addEventListener("load", l);
		}
	})();

	ChannelIO('boot', {
		"pluginKey" : "715d0551-4283-4f61-8c79-4d644c665858"
	});
</script>