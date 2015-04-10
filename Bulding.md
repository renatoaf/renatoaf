

# Introduction #

# Download and Install Java6 and Java7 #
Sigmah is a Java project. This project is using the GWT tools which only supports (for now) java 6. In order to build and run the project, we will need to install Java 6. However, we will also need to install Java 7 because this version of the java edition is required to run some component we will install later.

## Windows ##
To download this version, go to [Java SE Development Kit 7u51](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) and get the latest available Java SE Development Kit 7.
Then run the install program. You should keep the default settings. It will install the JDK, and the JRE

<img src='http://sigma-h.googlecode.com/svn/wiki/install/01_Java_install.png' border='1' height='300px' />

Now you should have “jdk1.7.0\_51” and “jre7” folders in the installation repository, which is by default for windows: “C:\Program Files\Java”.
To install Java 6 we have to do the same operations. The latest version is available here: [Java SE Development Kit 6u45](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)

**Warning:** to download early version of Java, you may have to create an Oracle account.

## Linux ##
If you are using a Linux based system, you may just install JDK 6 via command line:

```
sudo apt-get install openjdk-6-jdk
```

To find in which folder your JDK was installed, use:

```
echo $JAVA_HOME
```

On Ubuntu 13.10, for example, the JDK installation folder is _/usr/lib/jvm/_.

# Eclipse installation #
The procedure to install Eclipse is the same for both Linux and Windows systems.

## Download eclipse ##
Eclipse is available on the [Eclipse download page](http://www.eclipse.org/downloads/)
For this tutorial, we will use the latest eclipse version this date, which is “Eclipse Kepler 4.3.2”. After having downloading the zip file, just unzip it into the repository you want.

## Before launch ##
Before launching Eclipse, we will set the default jvm. Go to your Eclipse repository, and open the “eclipse.ini” file. Add the option “-vm path/to/jvm/bin” at the very beginning of the file.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/02_eclipse.ini.png' border='1' height='300px' />

## Set others jvm ##
We need now to set Java 6 into the Eclipse environment. Go to the “Preferences” options.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/03_open_preference.png' border='1' height='300px' />

Open Java > Installed JREs
We already have the jdk 7 installed. We will add jdk 6. Click « Add… ».

<img src='http://sigma-h.googlecode.com/svn/wiki/install/04_add_JRE.png' border='1' height='300px' />

Select Standard VM, and browse to the java 6 directory. Eclipse should then find it. Click Finish.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/05_add_JRE.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/06_add_JRE.png' border='1' height='300px' />

Now close the Preferences window, and re-open it. Open Java > Installed JREs > Execution Envronment. Check the JREs corresponding to the Execution Envirronment for Java SE-1.6 and Java SE-1.7.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/07_add_JRE.png' border='1' height='300px' />

## Plugins installation ##
Eclipse plugins are available in the “Help” menu, “Eclipse Marketplace…” option.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/08_Marketplace.png' border='1' height='300px' />

### Subclipse ###
Subclipse is an Eclipse support for subversion. It is used to get the sigmah project, and synchronize with the remote sigmah repository. Get “Subclipse 1.10.4” or newer.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/09_install_subclipse.png' border='1' height='400px' />

Click Install > Select all > confirm > accept agreements > finish

You may restart Eclipse before continuing.

### Maven plugin ###
Maven is a build manager for java. We will use “Maven integration for Eclipse 1.4” which allow using Maven with Eclipse.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/10_install_maven.png' border='1' height='400px' />

Click Install > Select all > confirm > accept agreements > finish

You may restart Eclipse before continuing.

### GWT plugin ###
Sigmah project is a full GWT app. This plugin integrates the “Google Web Toolkit” tools into Eclipse. Get the “Google plugin for Eclipse 4.3” or newer. You must have the jdk 1.7 installed to be able to run this plugin, but keep in mind that the Sigmah project is built under java 1.6.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/11_install_gwt.png' border='1' height='400px' />

Click Install > Select all except “google app engine tools for Android” > confirm > accept agreements > finish

You may restart Eclipse before continuing.

## Get Sigmah project on Eclipse ##
We will create a new project using Eclipse. Go to File > new > other. Search for SVN, and select “Checkout Projects from SVN”.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/12_create_project.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/13_create_project.png' border='1' height='300px' />

For the location, enter the following URL: http://sigma-h.googlecode.com/svn/trunk, and then select the “sigma” folder.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/14_create_project.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/15_create_project.png' border='1' height='300px' />

Thick “Check out as a project in the workspace”, give the name you want and go to Next.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/16_create_project.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/17_create_project.png' border='1' height='300px' />

The new created project should appear into the Package/Project explorer tab.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/18_Package_explorer.png' border='1' />

Now we will convert this project to a “Maven project”. Right click on it, Configure > Convert to Maven Project.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/19_Convert_to_maven.png' border='1' height='400px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/20_Convert_to_maven.png' border='1' />

# Add missing libraries #
#### Important temporary note: ####
Currently, the maven build will not work due to missing library on the Maven repository. We're fixing this by setting up a new Maven repository elsewhere. In the meantime, the solution is to download and install those libraries manually.

## Get Apache maven ##

### Windows ###
First you will need to get the “real” Apache Maven. Go to the [Apache download page](http://maven.apache.org/download.cgi) and get the last version (3.2.1) and unzip it anywhere on your computer.

### Linux ###
To install Maven on Unix based-systems, you may use apt-get:

```
sudo apt-get install maven
```

## Installing libraries ##
Now we will use this Maven to get the missing libraries.
The jar libraries can be downloaded from this [location](http://code.google.com/p/sigma-h/downloads/detail?name=missing-jar-to-build-Sigmah.zip). Then unzip the archive.

Go to your maven folder, open bin, and form there run:
```
mvn install:install-file -Dfile=..\..\missing-jar-to-build-Sigmah\gwt-maps-1.1.1.jar -DgroupId=com.google.gwt.google-apis -DartifactId=gwt-maps -Dversion=1.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=..\..\missing-jar-to-build-Sigmah\gxt-2.2.5-gwt22.jar -DgroupId=com.extjs -DartifactId=gxt -Dversion=2.2.5-gwt22 -Dpackaging=jar
```

# Set up Sigmah database #
As a developer, before you test the Sigmah project, you have to set up your own database system. Sigmah is now based on the database system MySql 5. But it also works with PostgreSQL 8 +. For the instructions purpose, we will use PostgreSQL 9.3.

## PostgreSQL installation ##
### Windows ###
First, download [PostgreSQL for windows](http://www.postgresql.org/download/windows/). The version includes pgAdmin III which is a useful tool to set up the DB.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/21_Install_pgSQL.png' border='1' height='300px' />

You can keep default parameters:
  * Repository : C:\Program Files\PostgreSQL\9.3
  * Data repository : C:\Program Files\PostgreSQL\9.3\data
  * Port : 5432

### Linux ###
...

## Database creation using pgAdmin III ##
Now you should have a PostgreSQL server and pgAdmin III installed. Open pgAdmin III.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/22_pgAdminIII.png' border='1' height='400px' />

Connect to your server, and add a new database.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/23_pgAdminIII.png' border='1' height='400px' />

Name it “sigmah”, keep “postgres” as the owner, and UTF8 encoding.
Now we will run scripts to create the tables and populate it. [Get the scripts](http://code.google.com/p/sigma-h/downloads/detail?name=MinimumDataKit-1.2-postgresql.zip&can=2&q=)
Right click on the database, and “Script CREATE”

<img src='http://sigma-h.googlecode.com/svn/wiki/install/24_pgAdminIII.png' border='1' height='300px' />

Run each of the four downloaded scripts in the given order:
  * 0-structure.postgres.sql
  * 1-actinfo-data-withoutUser.postgresql.sql
  * 2-compas\_quality\_framework.postgresql.sql
  * 3-DRC-adminentities.postgresql.sql

<img src='http://sigma-h.googlecode.com/svn/wiki/install/25_pgAdminIII.png' border='1' height='400px' />

Since the script are not kept up to date for each new sigmah version, you need to check out if there are differences between the last sigma model version, and the one you just installed. Go to [the changeLog page](https://code.google.com/p/sigma-h/wiki/SchemaChangeLog), and run the given extra scripts.

Now we have to run the new organization script. This will insert data of your (dummy) organization for dev purpose. [Get the scripts](http://code.google.com/p/sigma-h/downloads/detail?name=newOrganizationLaunchScript-1.2%2B.postgresql.sql&can=2&q=).
Before running it, you will have to replace some values by your own parameters:
| **Parameter to replace** | **Description** |
|:-------------------------|:----------------|
| §OrganizationName§ | The name of your organization |
| §OrganizationLogoFilename§ | The filename of the logo (i.e. logo.png) |
| §HeadquartersCountryCode§ | Use an ISO 2-letters code like "AF" from [Officially assigned code elements](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements) |
| §UserEmail§ | Your mail address |
| §UserName§ | Your name |
| §UserFirstName§ | Your first name |
| §UserLocale§ | Must be "fr" or "en" or "es" don't include quotes (") |

# Sigmah properties #
Now you can tell Sigmah how to connect to your database and email server. Open the “sigmah.properties” file, and replace the hooked parameters with your own values. Here is an example of the result.

```
# --
# DATABASE
# --

hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate.connection.driver_class=org.postgresql.Driver
hibernate.connection.username=postgres
hibernate.connection.password=root
hibernate.connection.url=jdbc:postgresql://localhost:5433/sigmah
hibernate.hbm2ddl.auto=update
hibernate.show_sql=true
hibernate.format_sql=true
```

Fill also file storage definitions.

```
# --
# FILES STORAGE
# -------------
# Files can be stored 2 ways :
# * local storage : files are stored on the local disk
# * s3 storage : files are stored in buckets with the AWS infrastructure
# --

# Storage policy (if missing or error, the default policy is the local one)
# * Local : org.sigmah.server.endpoint.file.LocalStorageProvider
# * S3 : org.sigmah.server.endpoint.file.S3StorageProvider
repository.file_storage_provider_class=org.sigmah.server.endpoint.file.LocalStorageProvider

# Root directory name (local) or bucket name (s3) where files are stored.
repository.name=C:/Users/jbr/Documents/Dev/Workspaces/Sigmah/files

#Maximum size of the uploaded files (bytes)
upload.maxSize=20971520

# Sub directory name (local) or key prefix (s3) where dynamically served images are stored.
repository.images=images

# Credentials, only for s3 storage.
# aws.access_key=<aws access key>
# aws.secret_key=<aws secret key>
```

... and mail properties. You may use [Restricted Gmail SMTP server](https://support.google.com/a/answer/176600?hl=en) for tests, since it does not require authentication. See this [link](https://support.google.com/a/answer/176600?hl=en) to understand its limitations.

```
# --
# MAILS
# --

mail.hostname=aspmx.l.google.com
mail.port=25
mail.from.address=<email_address>
mail.from.name=Sigmah Development Tests
```

# Run Sigmah in Development Mode #
Before running Sigmah, make sure you have JDK 1.6 installed on your computer, and set in the Eclipse > preference > Compiler.
To run the project, select it in the package explorer and deploy “Run > Run Configuration…”. Right click on “Maven build > new”.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/26_Run_sigmah.png' border='1' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/27_Run_sigmah.png' border='1' height='400px' />

Add goals “clean” and “gwt:run”, set the profiles as “sigmah” and check “skip tests”. Don’t forget to point out your base directory. In the JRE tab, select jdk1.6 in Runtime JRE. Apply and Run.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/28_Run_sigmah.png' border='1' height='400px' />

Once the project is running, you should have a GWT development mode window open. From there you can open the app in your default browser. On the first time you run it, your browser will ask you to download and install the “GWT Developer Plugin”. Install it and you will be able to access Sigmah.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/29_Run_sigmah.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/30_Run_sigmah.png' border='1' height='300px' />
<img src='http://sigma-h.googlecode.com/svn/wiki/install/31_Run_sigmah.png' border='1' height='300px' />

#### Alert for Linux developers ####
If you use Linux, at least temporarily, GWT Developer Plugin is not supported anymore on Google Chrome (from version 35). You should therefore downgrade your Google Chrome to version 34 and avoid automatic updates.

As for Mozilla Firefox, the last supported versions are Firefox 24 ESR and the non-ESR version up to version 26.

See [this](https://groups.google.com/d/msg/google-web-toolkit/yGERgZLWjAc/M2rtUYFF29AJ) discussion for further references.

# Run Sigmah on an external server #
## Install Tomcat7 ##
### Windows ###
[Get Tomcat7 here](https://tomcat.apache.org/download-70.cgi)
Get the zip file for your OS (not the installer)

Run:
```
`<path>`/bin/sartup.bat #to start the tomcat7 server
`<path>`/bin/shutdown.bat #to stop the tomcat7 server
```

### Linux ###
You can install Tomcat7 via command line:

```
sudo apt-get install tomcat7
```

Run:
```
sudo service tomcat7 start #to start the tomcat7 server
sudo service tomcat7 stop #to stop the tomcat7 server
```

## Create and deploy the Sigmah Package ##
To create the war of the application, we will create a new run Configuration.

Add goals “clean” and “gwt:package”, set the profiles as “sigmah” and check “skip tests”. Don’t forget to point out your base directory. In the JRE tab, select jdk1.6 in Runtime JRE. Apply and Run.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/32_Ext_server.png' border='1' height='400px' />

The packaging can take a while, because of the multiple permutations we have (nb of languages `*` nb of supported browser). After building success, refresh your project (F5 on the project in the package explorer), and you should have a new war file in the “target” folder.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/33_Ext_server.png' border='1' height='300px' />

Copy this file, and paste it in the “webapp” folder of your tomcat7 repository. Then start tomcat7. A new folder, named as the war file should be created.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/34_Ext_server.png' border='1' />

Now try to go to <http://localhost:8080/appName>. Here it would be: http://localhost:8080/sigmah-1.3-rc1

**NB:** Before starting the server, you can give the war any name that fits you. Then, change the url a you will be able to access the app. That way you can keep a history of your built war.

**Warning:** If you want to rebuilt the app with a new war, shutdown your tomcat server, delete the old war AND the old app folder, and only then you can paste the new war and start the server.

# Known bugs #
## Out of memory error ##
When you launch sigmah in GWT Development Mode, you may have an "OutOfMemory:PermSpace" error. To fix this error, you should specify a bigger PermSpace in the Pom.xml file, which is in your project root directory. In the “extraJvmArgs” tag, add the option –XX:MaxPermSize=512M (or more).
```
<!-- configure the GWT-Maven plugin -->
<plugin>
﻿  <groupId>org.codehaus.mojo</groupId>
﻿  <artifactId>gwt-maven-plugin</artifactId>
﻿  <version>${gwt.maven.plugin.version}</version>

﻿  <configuration>
﻿  ﻿  <runTarget>/</runTarget>
﻿  ﻿  <logLevel>TRACE</logLevel>
﻿  ﻿  <inplace>true</inplace>
﻿  ﻿  <gwtVersion>${com.google.gwt.version}</gwtVersion>
﻿  ﻿  <port>9090</port>
﻿  ﻿  <module>org.sigmah.ActivityInfoProduction</module>
﻿  ﻿  <extraJvmArgs>-Xmx512M -Xss1M -XX:MaxPermSize=512M</extraJvmArgs>
﻿  </configuration>
```

## Missing artifact error ##
You may have an error in the pom.xml: “Missing artifact com.sun:tools:jar:1.5.0”. To fix this, go to your Eclipse repository, and open the “eclipse.ini” file. Add the option “-vm path/to/jvm/bin” at the very beginning of the file.

<img src='http://sigma-h.googlecode.com/svn/wiki/install/02_eclipse.ini.png' border='1' height='300px' />