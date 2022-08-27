<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/theme.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#if codeSnippet??>
    <span id="load_date">${codeSnippet.date()}</span><br>
    <#if codeSnippet.restricted()>
        <#if codeSnippet.restrictedByViews()>
            <#if codeSnippet.views() gt -1>
                <span id="views_restriction">${codeSnippet.views()} more views allowed</span><br>
            </#if>
        </#if>
        <#if codeSnippet.restrictedByTime()>
            <#if codeSnippet.time() gt 0>
                <span id="time_restriction">The code will be available  for ${codeSnippet.time()} seconds</span><br>
            </#if>
        </#if>
    </#if>
    <pre id="code_snippet"><code>${codeSnippet.code()}</code></pre>
<#else>
    No Code snippet found
</#if>
</body>
</html>