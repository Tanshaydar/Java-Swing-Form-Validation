This little piece of code lets you to add validation to your Java Swing application forms.
You can set verification to your JTextFields and JTextAreas with setInputVerifier(); method, which is a native Java method.

Types of verifications this little piece of code supports are:
* Integer only w/ min and max values.
* Double only w/ min and max values. 
* Max text length check.
* Not null check.

It lets you to disable your Save and/or Update buttons on the fly if any wrong input is given, or user skipped a not nullable area.