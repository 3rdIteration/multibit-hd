# MultibitHD Key Extraction Modification

The easiest way for me was to hook into MultiBit HD's signing capabilities and log the private key being used to the console:

```
diff --git a/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java b/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
index 61979a3..e8a0de1 100644
--- a/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
+++ b/mbhd-core/src/main/java/org/multibit/hd/core/managers/WalletManager.java
@@ -1753,6 +1753,8 @@ public enum WalletManager implements WalletEventListener {
           if (signingKey.getKeyCrypter() != null) {
             KeyParameter aesKey = signingKey.getKeyCrypter().deriveKey(walletPassword);
             ECKey decryptedSigningKey = signingKey.decrypt(aesKey);
+            log.info("HACK address: " + signingAddress.toString());
+            log.info("HACK private key: " + decryptedSigningKey.getPrivateKeyAsWiF(networkParameters));

             String signatureBase64 = decryptedSigningKey.signMessage(messageText);
             return new SignMessageResult(Optional.of(signatureBase64), true, CoreMessageKey.SIGN_MESSAGE_SUCCESS, null);
```

I have tested this with v0.5 following these steps:

1) Clone the MultiBit HD Git repository
2) Apply the patch above (The current repo where you find this file has been pre-patched)
3) Build the project (Skipping tests to speed things up): mvn clean dependency:sources install -DskipTests
4) Start the application: java -jar mbhd-swing/target/multibit-hd.jar
If the app just crashes straight away, you need to try an older version of Java like JRE 1.7.0... 
5) Go to Tools -> Sign message and enter a Bitcoin address from your wallet for which you want to recover the private key. Press finish.
6) Read the Bitcoin address and corresponding private key from the console output

# Multibit is Deprecated - Do Not Use

Wednesday, July 26, 2017

Dear Bitcoin Community,

It is time for us to let Multibit go. 

KeepKey acquired Multibit a little over 1 year ago. At the time, the engineers who originally built and supported Multibit had announced that they would no longer be working on it or providing support. Multibit played an important role in the Bitcoin infrastructure. We felt that it was important for Multibit to continue and hoped that with our existing support and development teams, we would be able to keep Multibit alive.

The reality is that Multibit is in need of a lot of work. It has stubborn bugs that have caused us and Multibit users much grief. Additionally, Bitcoin has gone through a fundamental change in regards to the way fees work. The addition of SegWit in the coming weeks will mean the Multibit software has fallen still further behind.

Unfortunately, KeepKey simply does not have the resources to support the current issues, nor to rebuild Multibit to ensure ideal user experience. By focusing our attention on the KeepKey device, we will continue building and improving the best hardware wallet available.

Thus, KeepKey will discontinue support and maintenance of Multibit, effective immediately.

We recommend that all Multibit users discontinue using it and you move your keys to other wallet software of your choosing. 

## Next Steps for Multibit Users 
Videos that demonstrate how to move your wallet to Electrum are available on YouTube.

- Multibit HD: https://youtu.be/E-KcY6KUVnY
- Multibit Classic: https://youtu.be/LaijbTcxsv8

Please note that the version of Electrum available for download today (version 2.8.3) doesn’t fully support the importing Multibit HD wallet words. The version shown in the Multibit HD video is the soon-to-be-released next version.

Multibit was a fantastic piece of software in its time, and we want to thank the Multibit developers for such an important contribution to Bitcoin’s history.

Sincerely,

Ken Heutmaker

CTO, KeepKey 

------

Build status: [![Build Status](https://travis-ci.org/keepkey/multibit-hd.png?branch=develop)](https://travis-ci.org/keepkey/multibit-hd)

Project status: Pre-release. Expect minor bugs and UI adjustments. Suitable for small scale production.

### MultiBit HD (MBHD)

A desktop Hierarchical Deterministic Wallet (HDW) for Bitcoin using the Simplified Payment Verification (SPV) mode to provide very fast block chain synchronization.

The target audience is "international mainstream" which compels the user interface to remain as simple and consistent as possible while still retaining advanced capabilities
under the covers.

Support for external hardware wallets (such as the Trezor) is [available through the MultiBit Hardware project](https://github.com/keepkey/mbhd-hardware).

### Main website

Pre-packaged installers are available from the [MultiBit website](https://beta.multibit.org).

### Technologies

* Java 7 and Swing
* [Bitcoinj](https://github.com/bitcoinj/bitcoinj) - Providing various Bitcoin protocol utilities (GitHub is the reference)
* [hid4java](https://github.com/gary-rowe/hid4java) - Java library providing USB Human Interface Device (HID) native interface
* [MultiBit Hardware](https://github.com/keepkey/multibit-hardware) - Java library providing Trezor support
* [Google Protocol Buffers](https://code.google.com/p/protobuf/) (protobuf) - For use with serialization and hardware communications
* [Font Awesome](http://fortawesome.github.io/Font-Awesome/) - for iconography
* [Install4j](https://www.ej-technologies.com/download/install4j/files) - for a smooth installation and update process

### Getting started

MultiBit HD is a standard Maven build from a GitHub repository and currently relies on some builds of libraries which aren't available in Maven Central.

Below are some basic instructions for developers - there is [more information in the MultiBit HD wiki](https://github.com/keepkey/multibit-hd/wiki).

#### Verify you have Git

```
$ git --version
```

[Install git](https://help.github.com/articles/set-up-git/) if necessary.

Then, if this is your first time working with MultiBit HD source code, clone the source code repository (over HTTPS) using:

```
$ git clone https://github.com/keepkey/multibit-hd.git
```
A sub-directory called `multibit-hd` will be created which is your project root directory.

To update a previous clone of MultiBit HD use a pull instead:

```
$ cd <project root>
$ git pull
```

#### Verify you have Maven 3+

Most IDEs (such as [Intellij Community Edition](http://www.jetbrains.com/idea/download/)) come with support for Maven built in, but if not then you may need
 to [install it manually](http://maven.apache.org/download.cgi).

IDEs such as Eclipse may require the [m2eclipse plugin](http://www.sonatype.org/m2eclipse) to be configured.

To quickly check that you have Maven 3+ installed check on the command line:
```
$ mvn --version
```
Maven uses a file called `pom.xml` present in the MultiBit HD source code project directory to provide all the build information.

#### We currently use a forked version of Bitcoinj

The [MultiBit Staging repository](https://github.com/keepkey/mbhd-maven) contains a fork of the bitcoinj library
and its supporting Orchid JAR for Tor that is aligned with the MultiBit HD `develop` branch. This should be used for
development builds and is suitable for production. As we make changes to our fork we update the staging repository. 

Our release cycle is different to that of bitcoinj and our version reflects as accurately as we can the state of play
when the bitcoinj fork code was frozen. For example `bitcoinj-0.13-SNAPSHOT-alice-0.0.9` should be interpreted as
"a snapshot of upstream bitcoinj 0.13 that has additional code (alice) that is released under version 0.0.9".

Deeper analysis of the actual git upstream can be seen through the tagging of the `master` branch.

Wherever possible, and time permitting, we will introduce our forked changes as a pull request into the upstream bitcoinj
so that other projects can benefit but we must use a fork to ensure rapid updates are possible during development.

Anyone wishing to handle this part of the process is very welcome to offer up their assistance!

#### Start the application (from an IDE)

To run the application within an IDE, simply execute `MultiBitHD.main()` in the `mbhd-swing` module. No command line parameters are needed, although a Bitcoin URI is accepted.

#### Start the application (from the command line)

To run the application from the command line, first build from the project root directory (pulling in all sources from upstream):
```
$ cd <project root>
$ mvn clean dependency:sources install
```
then start the application using the shaded JAR:
```
$ java -jar mbhd-swing/target/multibit-hd.jar
```
No command line parameters are needed, although BIP 21 and BIP 72 Bitcoin URIs are accepted. In the example below a BIP 21 Bitcoin URI
is presented, the quotes are required to avoid URL decoding:
```
$ java -jar mbhd-swing/target/multibit-hd.jar "bitcoin:1AhN6rPdrMuKBGFDKR1k9A8SCLYaNgXhty?amount=0.01&label=Please%20donate%20to%20multibit.org"
```
#### Multiple instances

MultiBit HD will avoid multiple instances by using port 8330 as a method of detecting another running instance. If port 8330 cannot be bound MultiBit HD will assume that
another instance is running and hand over any Bitcoin URI arguments present when it started. It will then perform a hard shutdown terminating its own JVM. The other instance will
react to receiving a Bitcoin URI message on port 8330 by displaying an alert bar requesting the user to act upon the Bitcoin URI payment request.

### Frequently asked questions (FAQ)

Here are some common questions that developers ask when they first encounter MBHD.

### How can I contribute ?

Simply add yourself as a watcher to the repository and keep and eye on issues that interest you.

In general issues are labelled with a yellow `awaiting review` or a blue `in progress` to indicate where our attention is focused. We would appreciate you updating and running up
the code and verifying that an `awaiting review` does what it is supposed to. If you could then post a comment similar to `Works for me on Linux` then that would help us to close
off the issue faster, or engage further with it to get bugs fixed.

Of course, if you want to contribute coding effort or deeper code review and commentary that would be most appreciated as well. We want MultiBit HD to be as solid as we can make it.

As always, donations to the MultiBit address are welcome: [1AhN6rPdrMuKBGFDKR1k9A8SCLYaNgXhty](bitcoin:1AhN6rPdrMuKBGFDKR1k9A8SCLYaNgXhty?amount=0.01&label=Please%20donate%20to%20multibit.org).

#### Where's the Trezor support ?

We've begun the process of integrating the Trezor device through the [MultiBit Hardware](https://github.com/keepkey/multibit-hardware) project. If you are a hardware
wallet developer, or are just interested to learn how the Trezor device works under the covers please watch this project.

From now you should be able to attach your Trezor device and see an alert message providing information and a button to switch your current wallet over to one that tracks your
Trezor device. All private keys remain on the Trezor device and associated contacts, payments and so on are keep encrypted in your wallet.

#### Why not Java 8 ?

At the time MBHD was being written (Q4 2013 - Q2 2014) Java 8 was not in production release and the sheer size of the packaged download was coming in at 150Mb (18x MultiBit
Classic and 3x the standard Java 7 packaged footprints). That footprint alone would be sufficient to dramatically increase the cost of serving the application and deter people
from downloading in countries where bandwidth is less available.

We will revisit this once we have suitable Install4j JREs available. We expect this to occur shortly after [Release 0.1](https://github.com/keepkey/multibit-hd/milestones).

#### Why not JavaFX ?

JavaFX was only available as version 2.2 on Java 7 and the move to Java 8 was not going to happen. There were many significant features missing in JavaFX 2.2 which would only be
fixed in Java 8:

* no right to left languages (Hebrew, Farsi, Arabic, etc)
* no integration with native platform for Bitcoin URI protocol handling (no BIP 21 or 72 support)
* no reporting uncaught exceptions (no error reporting)

Thus this technology was not suitable for the very wide range of people using MultiBit in all corners of the globe.

#### Why Swing ?

There is a vast amount of support for Swing. The code is near bullet-proof for most use cases and it fully supports internationalization which is a key requirement for MultiBit HD.
Also, many of the supporting libraries for Swing
pre-date 2009 making it much harder for [dependency chain attacks](http://gary-rowe.com/agilestack/2013/07/03/preventing-dependency-chain-attacks-in-maven/) to take place.

With some effort Swing can be made to look quite modern.

Swing also allows us to smoothly integrate with the native platform which puts it ahead of JavaFX until at least Q3 2015.

#### Why not SwingX ?

SwingX is a large support library that introduces a lot of additional functionality to Swing applications. Much of this additional functionality is not required by MultiBit or
can be relatively easily worked around. Consequently including it would increase the available attack surface.

#### Why the Nimbus look and feel ?

In Java 7 the Nimbus look and feel became integrated with the JDK. It provides a modern 2D rendered UI that is the same across all platforms. It is highly customisable through
simple themes and provides consistent painting behaviour across platforms. For example to paint a button red in Swing using the Mac-only Aqua theme requires complex custom
ButtonUI code.

Using Nimbus ensures that we don't have this or similar problems.

[Technical details on the default colours](http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html#primary)

#### I want an installer not this IDE

Installers lag the latest changes by a few weeks. You can find them on the [MultiBit HD website](https://www.multibit.org/releases/multibit-hd).

As new changes are released they will update automatically over HTTPS from 0.0.4 (Private Beta 4) onwards.

#### Is there a developer wiki ?

Yes. [The wiki pages](https://github.com/keepkey/multibit-hd/wiki/_pages) provide comprehensive instructions for developers that cover a variety of environments.

#### What is your development roadmap ?

We are currently working to the following timetable:

1. Hardware wallet (Trezor) support (see [version 0.0.5 issues](https://github.com/keepkey/multibit-hd/milestones/Beta%205%20Trezor))
1. BIP70-73 payment protocol support (see [version 0.1.0 issues](https://github.com/keepkey/multibit-hd/milestones/Release%200.1%20Payment%20Protocol))
1. Hierarchical deterministic multi-signature (HDM) support (still planning)

## Developer tools

This section covers tools to assist developers when developing MBHD code.

### Executing the automated requirements tests

We use [Swing FEST](http://docs.codehaus.org/display/FEST/Swing+Module) to perform automated requirements testing of the user interface. It gives super fast overview of the
application and runs like a standard unit test. `MultiBitHDFestTest` provides the entry point.

This provides an ever-improving set of regression tests to ensure that new code does not break the existing work.

The code is arranged as a single test case with multiple individual tests that are independent of each other. Each create their own temporary application directory and may or
may not require an initial randomly created empty wallet.

Developers are strongly encouraged to create a FEST test for any UI work they are about to undertake and use it to actually test the work in progress. It is far faster to run
FEST than to manually run up the application and do it manually.

FEST is not intended to run as part of a Maven build since not all build environments support a display.

### Upgrading Font Awesome

Use the `FontAwesomeTools` to create the necessary enum entries for `AwesomeIcon` as required.

### Changing resource bundles

Use the `ResourceBundleTools` to find similar entries and to arrange keys in the same order across all bundles.

### Updating the internal help files

To do an internal help refresh:

1. Ensure you have cloned the [MultiBit website](https://github.com/keepkey/multibit-website) into a sibling directory to MultiBit HD
1. Switch to the appropriate site branch (e.g. `master` for ongoing releases) and update
1. Use Ant to execute the `update-internal-help.xml` script to copy the relevant files into the correct locations
1. Within Intellij, navigate to `assets/images/en/screenshots/mbhd-01`, select all image files and copy to clipboard
1. Paste image names into `HelpScreenView` where directed (remove existing)
1. Update any FEST tests that rely on particular text being in place
1. Run without a network connection to verify that the internal help appears correctly
1. Check internal and external links work correctly
1. Check IDE Changes to ensure no new files have been missed

#### Updating the internationalisation files

To do an i18n refresh:

1. [Download the i18n zip](http://translate.multibit.org/project/multibit-hd) using the Download button
2. Unzip this to a directory `multibit-hd`
3. Copy `multibit-hd` to: `mbhd-swing/src/main/resources/languages-from-crowdin`
4. Run a terminal and cd to `mbhd-swing/src/main/resources/languages-from-crowdin`
5. Run the script `./move-files.sh`

This moves and renames all the files into the languages directory.
If there are any files or new languages it lists them at the end so that you can see there is something missing from the `move-files` script.
