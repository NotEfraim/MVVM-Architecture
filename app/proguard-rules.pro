# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# random seed
-obfuscationdictionary build/tmp/dict.txt
-classobfuscationdictionary build/tmp/dict.txt
-packageobfuscationdictionary build/tmp/dict.txt

# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

#The code obfuscation compression ratio ranges from 0 to 7. The default value is 5 and is generally not modified
-optimizationpasses 5

#Mixed case is not used when mixing, and the mixed class name is lowercase
-dontusemixedcaseclassnames

#Specifies that class members that are not public libraries are not ignored
-dontskipnonpubliclibraryclassmembers


#Without preverification, preverify is one of the four steps of proguard, and Android does not require preverify to speed up obfuscation.
-dontpreverify

#Leave the Annotation unobfuscated
-keepattributes *Annotation*

#Avoid Obfuscating Generics
-keepattributes Signature

#Use printmapping to specify the name of the mapping file
-verbose
-printmapping proguardMapping.txt

#don't easily track
-flattenpackagehierarchy

# Keep Serialize Class
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep Hilt annotations and generated code
-dontwarn javax.inject.**
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep interface dagger.hilt.** { *; }

# Keep Hilt entry points
-keepclasseswithmembers class * {
    @dagger.hilt.* <methods>;
}

# Keep Hilt components
-keep,allowobfuscation interface dagger.hilt.*Component
-keep,allowobfuscation interface dagger.hilt.*Component$Builder
-keep,allowobfuscation interface dagger.hilt.*EntryPoints

# Keep Retrofit classes
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

# Keep Retrofit annotation classes
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-keepclassmembers class ** {
    @retrofit2.* <methods>;
}

# Preserve Retrofit interface annotations for serialization/deserialization
-keep,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep OkHttp classes
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }

# Keep OkHttp logging classes (if used)
-dontwarn okhttp3.logging.**
-keep class okhttp3.logging.** { *; }

# Keep Gson classes
-keepclasseswithmembers,allowobfuscation,includedescriptorclasses class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keepclassmembers enum * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# APP
# Rename Classes
-renamesourcefileattribute SourceFile

#Keep Model
-keepclassmembers class com.project.architecture.data.model.** {*;}
#Keep UI
-keep class com.project.architecture.ui.*{*;}