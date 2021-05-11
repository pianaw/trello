(function (d, w, m) {
    window.supportAPIMethod = m;
    var s = d.createElement('script');
    s.type = 'text/javascript';
    s.id = 'supportScript';
    s.charset = 'utf-8';
    s.async = true;
    var id = '355d0132bdf8f0699855de7159e769af';
    s.src = 'https://lcab.talk-me.ru/support/support.js?h=' + id;
    var sc = d.getElementsByTagName('script')[0];
    w[m] = w[m] || function () {
        (w[m].q = w[m].q || []).push(arguments);
    };
    if (sc) sc.parentNode.insertBefore(s, sc);
    else d.documentElement.firstChild.appendChild(s);
})(document, window, 'TalkMe');
