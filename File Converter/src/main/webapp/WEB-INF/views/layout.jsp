<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.amber-indigo.min.css" />
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>
	<title>file converter</title>
</head>
<body>
	<!-- Always shows a header, even in smaller screens. -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
  <header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
      <!-- Title -->
      <span class="mdl-layout-title">File Converter</span>
      <!-- Add spacer, to align navigation to the right -->
      <div class="mdl-layout-spacer"></div>
      <!-- Navigation. We hide it in small screens. -->
      <nav class="mdl-navigation mdl-layout--large-screen-only">
        <a class="mdl-navigation__link" href="">About</a>
        <a class="mdl-navigation__link" href="Signup">SignUp</a> <!-- working bla bla -->
        <a class="mdl-navigation__link" href="login">Login</a>
        <a class="mdl-navigation__link" href="welcome">Convert</a>
   
       
      </nav>
    </div>
  </header>
  
   <div class="mdl-layout__drawer">
    <span class="mdl-layout-title">File Converter</span>
    <nav class="mdl-navigation">
      <a class="mdl-navigation__link" href="">About</a>
      <a class="mdl-navigation__link" href="Signup">SignUp</a> <!-- bla bla link href  to controller -->
      <a class="mdl-navigation__link" href="login">Login</a>
      <a class="mdl-navigation__link" href="welcome">Convert</a>
    </nav>
  </div> 
  <main class="mdl-layout__content">
    <div class="page-content"><h1>WELCOME TO THE FILE CONVERTER</h1></div>
  </main>

</div>

</body>
</html>