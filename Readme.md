SugarSMV: syntactical sugar for the NuSMV model checker
=======================================================

[![Travis](https://img.shields.io/travis/liflab/sugarsmv.svg?style=flat-square)]()
[![SonarQube Coverage](https://img.shields.io/sonar/http/sonarqube.com/liflab:sugarsmv/coverage.svg?style=flat-square)]()

SugarSMV is a wrapper over the NuSMV model checker, adding various
"syntactical sugar" extensions to its original input language.

Repository structure                                           {#structure}
--------------------

The repository is separated across the following folders.

- `Core`: main source files
- `CoreTest`: test source files. You need to compile these files only
  if you want to run SugarSMV's unit tests.

Compiling the project contained in the present repository generates the
file `sugarsmv.jar`, which is the minimal file you need to run SugarSMV on
your system.

SugarSMV tries to have as few dependencies as possible. However, the
following companion library needs to be installed for SugarSMV to
compile and run:

- The [Bullwinkle parser](https://github.com/sylvainhalle/Bullwinkle),
  an on-the-fly parser for BNF grammars *(tested with version 1.3)*


Compiling and Installing SugarSMV                                {#install}
---------------------------------  

First make sure you have the following installed:

- The Java Development Kit (JDK) to compile. SugarSMV is developed to comply
  with Java version 6; it is probably safe to use any later version.
- [Ant](http://ant.apache.org) to automate the compilation and build process

Download the sources for SugarSMV from
[GitHub](https://github.com/liflab/sugarsmv) or clone the
repository using Git:

    git@github.com:liflab/sugarsmv.git

The repository is separated into multiple *projects*. Each of these
projects has the same Ant build script that allows you to compile them
(see below).

If the project you want to compile has dependencies,
 you can automatically download any libraries missing from your
system by typing:

    ant download-deps

This will put the missing JAR files in the `deps` folder in the project's
root. These libraries should then be put somewhere in the classpath, such as
in Java's extension folder (don't leave them there, it won't work). You can
do that by typing (**with administrator rights**):

    ant install-deps

or by putting them manually in the extension folder. Type `ant init` and it
will print out what that folder is for your system.

Do **not** create subfolders there (i.e. put the archive directly in that
folder).

### Compiling

Compile the sources by simply typing:

    ant

This will produce a file called `sugarsmv.jar` (or another library,
depending on what you are compiling) in the folder. This file
is runnable and stand-alone, or can be used as a library, so it can be moved
around to the location of your choice.

In addition, the script generates in the `doc` folder the Javadoc
documentation for using SugarSMV. To show documentation in Eclipse,
right-click on the jar, click "Properties", then fill the Javadoc location.

### Testing

SugarSMV can test itself by running:

    ant test

Unit tests are run with [jUnit](http://junit.org); a detailed report of
these tests in HTML format is availble in the folder `tests/junit`, which
is automatically created. Code coverage is also computed with
[JaCoCo](http://www.eclemma.org/jacoco/); a detailed report is available
in the folder `tests/coverage`.

Warning                                                          {#warning}
-------

The SugarSMV project is under heavy development. The repository may be
restructured, the API may change, and so on. This is R&D!

About the author                                                   {#about}
----------------

SugarSMV was written by [Sylvain Hallé](http://leduotang.ca/sylvain),
associate professor at Université du Québec à Chicoutimi, Canada. Part of
this work has been funded by the Canada Research Chair in Software
Specification, Testing and Verification and the
[Natural Sciences and Engineering Research Council
of Canada](http://nserc-crsng.gc.ca).
