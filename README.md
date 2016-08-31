# MApptrackerSDKDemo
艾瑞跟踪统计SDK使用示例Demo


<!DOCTYPE html>
<html class="theme theme-white">
<head>
<meta charset="utf-8">
<title>mApptracker SDK 使用说明</title>
<link href="https://www.zybuluo.com/static/assets/template-theme-white.css" rel="stylesheet" media="screen">
<style type="text/css">

#wmd-preview h1  {
    color: #0077bb; /* 将标题改为蓝色 */
}</style>
</head>
<body class="theme theme-white">
<div id="wmd-preview" class="wmd-preview wmd-preview-full-reader"><div class="md-section-divider"></div><div class="md-section-divider"></div><h1 data-anchor-id="gwlp" id="mapptracker-sdk-使用说明">mApptracker SDK 使用说明</h1><p data-anchor-id="66iu"><code>工具</code> <code>使用说明</code></p><blockquote data-anchor-id="vm35" class="white-blockquote">
  <p>版本号：V2.4.5</p>
</blockquote><div class="md-section-divider"></div><h2 data-anchor-id="w37s" id="此文为mapptracker-sdk-跟踪统计使用指引">此文为mApptracker SDK 跟踪统计使用指引</h2><hr><div class="md-section-divider"></div><h2 data-anchor-id="tybs" id="一开发工具的引入搭建">一.开发工具的引入搭建</h2><div class="md-section-divider"></div><h3 data-anchor-id="slw5" id="1androidstudio开发环境下的引入">1.AndroidStudio开发环境下的引入:</h3><ul data-anchor-id="f06a">
<li><p>在总工程构建脚本 <code>build.gradle</code> 文件中加入以下代码:</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="pln">allprojects </span><span class="pun">{</span></code></li><li class="L1"><code><span class="pln">    repositories </span><span class="pun">{</span></code></li><li class="L2"><code><span class="pln">        maven </span><span class="pun">{</span><span class="pln"> url </span><span class="str">"http://dpapi.irs01.com:8081/nexus/content/groups/public/"</span><span class="pln"> </span><span class="pun">}</span></code></li><li class="L3"><code><span class="pln">    </span><span class="pun">}</span></code></li><li class="L4"><code><span class="pun">}</span></code></li></ol></pre></li>
<li><p>在app Moduel中的<code>build.gradle</code>文件中的dependencies下加入一行代码引用sdk即可</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="pln">dependencies </span><span class="pun">{</span></code></li><li class="L1"><code><span class="pln">    compile </span><span class="str">'cn.com.iresearch.sdk:mapptracker:2.4.5@aar'</span></code></li><li class="L2"><code><span class="pun">}</span></code></li></ol></pre>

<p>至此AndroidStudio开发环境引入sdk配置完成</p></li>
</ul><div class="md-section-divider"></div><h3 data-anchor-id="xinu" id="2eclipse开发环境的导入">2.Eclipse开发环境的导入</h3><ul data-anchor-id="n7uv">
<li><p>先将开发工具包<a href="http://dpapi.irs01.com:8081/nexus/content/groups/public/cn/com/iresearch/sdk/mapptracker/2.4.5/mapptracker-2.4.5.aar" target="_blank">mapptracker-2.4.5.aar</a>下载到本地 <br>
<img src="http://static.zybuluo.com/wuseal/tcenb1tv8oresvvjh81isv4v/image_1an22ql30138lr8e1lmjp8oer99.png" alt="image_1an22ql30138lr8e1lmjp8oer99.png-15.2kB"></p></li>
<li><p>接着将工具包解压出来会得到如下文件 <br>
<img src="http://static.zybuluo.com/wuseal/0y0j0xsoer12ao0e2tfhqm54/image_1an4iq9sq2fe170e1p5u1bo0s7dm.png" alt="image_1an4iq9sq2fe170e1p5u1bo0s7dm.png-35.4kB" title=""></p></li>
<li><p>然后将jni目录下的内容覆盖到eclipse主工程的libs目录下，并将classes.jar也一并放入到主工程的libs目录下，若有重名问题，自行更改任意名字即可.</p></li>
<li><p>将AndroidManifest.xml里的一些权限如数复制到主工程的AndroidManifest.xml文件中既可 <br>
　 <br>
至此eclipse开发环境引入配置完毕</p></li>
</ul><div class="md-section-divider"></div><h2 data-anchor-id="3ui2" id="二sdk程序使用说明">二.SDK程序使用说明</h2><div class="md-section-divider"></div><h3 data-anchor-id="91s7" id="1渠道的配置有以下两种方法任选一种即可">1.渠道的配置：有以下两种方法，任选一种即可</h3><ul data-anchor-id="w8vx">
<li><p>方法一：在配置文件AndroidManifest.xml文件中进行配置，配置方式如下</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="tag">&lt;meta-data</span></code></li><li class="L1"><code><span class="pln">    </span><span class="atn">android:name</span><span class="pun">=</span><span class="atv">"ire_channel"</span></code></li><li class="L2"><code><span class="pln">    </span><span class="atn">android:value</span><span class="pun">=</span><span class="atv">"ireSearch"</span><span class="pln"> </span><span class="tag">/&gt;</span></code></li></ol></pre>

<p>其中的<code>value</code>值替换为相应的渠道名称值即可</p></li>
<li><p>方法二：在java代码里进行配置</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="com">/**</span></code></li><li class="L1"><code><span class="com"> * 设置渠道</span></code></li><li class="L2"><code><span class="com"> * Context context 上下文</span></code></li><li class="L3"><code><span class="com"> * String appChannel 渠道名称</span></code></li><li class="L4"><code><span class="com"> */</span></code></li><li class="L5"><code><span class="typ">IRMonitor</span><span class="pun">.</span><span class="pln">getInstance</span><span class="pun">().</span><span class="pln">setAppChannel</span><span class="pun">(</span><span class="typ">Context</span><span class="pln"> context</span><span class="pun">,</span><span class="pln"> </span><span class="typ">String</span><span class="pln"> appChannel</span><span class="pun">);</span></code></li></ol></pre></li>
</ul><div class="md-section-divider"></div><h3 data-anchor-id="z6ee" id="2sdk的初始化初始化过程推荐直接在application类中的oncreate方法中进行调用">2.SDK的初始化:初始化过程推荐直接在application类中的oncreate方法中进行调用</h3><ul data-anchor-id="w94s">
<li><p>直接初始化：</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> </span><span class="pun">初始化</span></code></li><li class="L1"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span></code></li><li class="L2"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> </span><span class="typ">Context</span><span class="pln"> context </span><span class="pun">上下文</span></code></li><li class="L3"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> appkey </span><span class="pun">艾瑞分配的</span><span class="pln">appkey</span></code></li><li class="L4"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> youUID </span><span class="pun">使用你们自己定义的</span><span class="pln">uid</span><span class="pun">，以便核对数据，(可为空，默认为</span><span class="pln">imei</span><span class="pun">)</span></code></li><li class="L5"><code class="language-/**"><span class="pln">     </span><span class="pun">*/</span></code></li><li class="L6"><code class="language-/**"><span class="pln">    </span><span class="typ">IRMonitor</span><span class="pun">.</span><span class="pln">getInstance</span><span class="pun">().</span><span class="pln">init</span><span class="pun">(</span><span class="kwd">this</span><span class="pun">,</span><span class="pln"> </span><span class="str">"appkey"</span><span class="pun">,</span><span class="pln"> </span><span class="str">"youUID"</span><span class="pun">);</span></code></li></ol></pre></li>
<li><p>多参数初始化：</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> </span><span class="pun">初始化</span></code></li><li class="L1"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span></code></li><li class="L2"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> </span><span class="typ">Context</span><span class="pln"> context </span><span class="pun">上下文</span></code></li><li class="L3"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> appkey </span><span class="pun">艾瑞分配的</span><span class="pln">appkey</span></code></li><li class="L4"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> youUID </span><span class="pun">使用你们自己定义的</span><span class="pln">uid</span><span class="pun">，以便核对数据，(可为空，默认为</span><span class="pln">imei</span><span class="pun">)</span></code></li><li class="L5"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> showLog </span><span class="pun">显示日志</span></code></li><li class="L6"><code class="language-/**"><span class="pln">     </span><span class="pun">*</span><span class="pln"> </span><span class="typ">IRCallBack</span><span class="pln"> </span><span class="pun">回调信息</span></code></li><li class="L7"><code class="language-/**"><span class="pln">     </span><span class="pun">*/</span></code></li><li class="L8"><code class="language-/**"><span class="pln">    </span><span class="typ">IRMonitor</span><span class="pun">.</span><span class="pln">getInstance</span><span class="pun">().</span><span class="pln">init</span><span class="pun">(</span><span class="kwd">this</span><span class="pun">,</span><span class="pln"> </span><span class="str">"appkey"</span><span class="pun">,</span><span class="pln"> </span><span class="str">"youUID"</span><span class="pun">,</span><span class="pln"> showLog</span><span class="pun">,</span><span class="pln"> </span><span class="typ">IRCallBack</span><span class="pun">);</span></code></li></ol></pre></li>
</ul><div class="md-section-divider"></div><h3 data-anchor-id="16j9" id="3跟踪统计功能的使用">3.跟踪统计功能的使用</h3><ul data-anchor-id="ns06">
<li><p>onResume方法说明 <br>
请在每个Activity的onResume()方法中调用如下代码</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="pln">    </span><span class="com">//onResume调用</span></code></li><li class="L1"><code><span class="pln">    </span><span class="typ">IRMonitor</span><span class="pun">.</span><span class="pln">getInstance</span><span class="pun">().</span><span class="pln">onResume</span><span class="pun">(</span><span class="kwd">this</span><span class="pun">);</span></code></li></ol></pre></li>
<li><p>onPause方法说明 <br>
请在每个Activity的onPause()方法中调用如下代码</p>

<pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code><span class="pln">    </span><span class="com">//onPause调用</span></code></li><li class="L1"><code><span class="pln">    </span><span class="typ">IRMonitor</span><span class="pun">.</span><span class="pln">getInstance</span><span class="pun">().</span><span class="pln">onPause</span><span class="pun">(</span><span class="kwd">this</span><span class="pun">);</span></code></li></ol></pre></li>
</ul><div class="md-section-divider"></div><h3 data-anchor-id="wpip" id="4混淆配置">4.混淆配置</h3><div class="md-section-divider"></div><pre class="prettyprint linenums prettyprinted" data-anchor-id="fsh6"><ol class="linenums"><li class="L0"><code><span class="pun">-</span><span class="pln">keep </span><span class="kwd">class</span><span class="pln"> cn</span><span class="pun">.</span><span class="pln">com</span><span class="pun">.</span><span class="pln">iresearch</span><span class="pun">.</span><span class="pln">mapptracker</span><span class="pun">.**{*;}</span></code></li></ol></pre><p data-anchor-id="c5yu">文档介绍完毕，请务必按以上步骤进行sdk的引入、配置和使用 <br>
使用示例请参考艾瑞官方<a href="https://github.com/wuseal/MApptrackerSDKDemo" target="_blank">demo</a></p><blockquote data-anchor-id="bydz" class="white-blockquote">
  <p>作者：<a href="https://github.com/wuseal" target="_blank">Seal</a> <br>
  2016年6月14日</p>
</blockquote></div>
</body>
</html>
