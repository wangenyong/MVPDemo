# Android MVP Demo

### 一堆废话
MVC在iOS上被苹果官方推崇，诚然在Android上并没有十分明显模式，就连谷歌也觉得不好意思，只能出面给开发者找出MVP，MVVM两条出路，以便解决项目壮大后臃肿的问题。

模式设计的目的其实很简单：为了更好的管理项目代码。而MVC模式寻求的是“M”和“V”的分离。

这个Demo只是提供一种思路，而非框架，也希望它能抛砖引玉。

### 一些弯路
我们为了实现某种功能的独立，我们会把它封装成自定义View。而View的监听事件则往往会与Activity绑定在一起（比如：Activity实现了View.OnClickListener接口）。

在简单的项目中这样可以实现快速开发，节省内存；而当遇到复杂界面时，这种高效的行为反而成为麻烦，大量的监听接口使得代码混乱，优化的效果也并非十分理想。

在Android上搭建MVC比较困难，其实仔细想想，Android上很多操作依赖Context来执行，而Activity也是经典的Context。[其实Activity就是天然的“C”](https://www.zhihu.com/question/19766132)，而所有View布局控件刚好是“V”，而谷歌也提供一套更合适的MVP方案（[MVC与MVP的差异直观的介绍](http://blog.csdn.net/duo2005duo/article/details/50594757/)）。

### 一些想法
由此我们不得不使用模式来解决问题。我的想法是，使用一个类来封装所有View的修改与监听；再由这个类来与Activity通讯，让Activity只负责业务上的逻辑，做一个更纯粹的类；而View与数据层的交互都是需要经过Activity这个业务员。

View封装 <====> Activity实现业务 <====> 数据层

Android的Activity有生命周期，开发时应该尽量适应其特性，能使用Server的时候也尽可能避免单例模型。

### Demo
这个Demo其实就是按照以上思路设计的，分为以下几个部分：

（PS：请自行忽略代码里的反射和声明，纯粹无聊之作）

#### Data Bean
在demo中，我只想让它能成为一个安静的数据结构。所有方法只是为了更好的表达自己或者最多也就修改一下自己的属性。它不应该做出越界的行为，不能改变其他对象。

#### Content View
它是负责View的逻辑，比如TextView修改文字，Button受点击，或者列表滚动时，ActionBar要改变透明度...

在与Activity通讯中，Demo中通过接口实现。在数据交互时会直接传递数据结构，原则上只能对其读取不能修改。

#### Activity Fragment
这两个做为有生命周期特征的业务，其实并没有什么毛病，我们可以理解为，业务本身也有周期性。

如果觉得它们的生命周期特征对业务的实现有影响，别忘了还有后台Server这个特殊的Context可以来帮忙。

#### Task
task为后台异步处理任务，根据需要可以使用线程池，HandleLooper，AsyncTask等等来实现。

一个Request可以对应一个网络接口，一个数据库读取操作，一件本地任务，或者是一套任务逻辑。并把处理的结果传递给Activity，Request作为后台任务不应该对Activity持有强引用。

#### 为什么没有 RxJava + Retrofit + dagger2
首先这个Demo只是演示，没必要那么复杂。

其次我喜欢简单，程序从简单到复杂，从复杂到结束（因为此时会引发重构）

最后这三套框架其实只是给我们提供另外的一直开发思路，它们也不是万金油，庞大的框架在简单的APP中未必能派上用场，舍本求末反而会成为累赘，适合自己的才是最好的。

学习编程的重点是学习其思想，而不是学习如何使用一套框架。假如有天找不到合适的轮子，我们可以自己造一个。

OOP（面向对象）出现的时候，人们以为POP（面向过程）的时代即将结束。然而这么多年过去了，现实告诉我们，他们各有各的世界。而编程世界里，活的最坚强的其实是BOP（面向Boss编程），认真你就输了！


License
-------

Copyright 2017 Yeamy.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
