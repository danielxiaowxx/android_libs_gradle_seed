## 项目介绍

该项目为开发android组件库的种子项目，所有组件为单独的子项目，提供发布到自己的hosted nexus maven仓库的功能

## 开始使用

先全局搜索替换，把android_libs_gradle_seed和com.dx替换成你喜欢的值，如用EmEditor将android_libs_gradle_seed全局替换成common_librady, com.dx替换成com.daniel

1. 根据实际情况修改根项目的gradle.properties

2. 增加组件（即module）

	New->Module，请参考EditText目录，然后根据实际情况修改gradle.properties

3. 测试组件（demo app）

	- 修改demo项目的build.gradle，使其依赖测试组件，如下所示依赖于EditText组件

			dependencies {
			    compile 'com.android.support:appcompat-v7:+'
			    compile project(":EditText")
			
			    compile fileTree(dir: 'libs', include: ['*.jar'])
			}

	- 新增测试组件的场景元素，如TestEditTextActivity和activity_test_edit_text.xml

	- 在AndroidManifest.xml中声明该Activiy为启动Activity，并把该activity放在其它activity的前面（确保第一个启动是它），如下所示。

	        <activity
	            android:name=".TestEditTextActivity"
	            android:label="@string/clearable_edit_text" >
	            <intent-filter>
	                <action android:name="android.intent.action.MAIN" />
	                <category android:name="android.intent.category.LAUNCHER" />
	            </intent-filter>
	        </activity>