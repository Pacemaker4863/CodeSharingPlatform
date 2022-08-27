<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/css/theme.css">
    <script src="/js/ajax.js" language="JavaScript"></script>
</head>
<body>
<textarea id="code_snippet"></textarea>
<br><br>
<label for="time_restriction">Time restriction (is seconds)</label><br>
<input id="time_restriction" type="text"/>
<br><br>
<label for="views_restriction">View restriction (Number of views)</label><br>
<input id="views_restriction" type="text"/>
<br><br>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>
</body>

</html>