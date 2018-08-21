# ImageOptimization
高效加载大图、多图解决方案，有效避免程序OOM


1.LruCache来缓存图片，所以不需要担心内存溢出的情况，当LruCache中存储图片的总大小达到容量上限的时候，会自动把最近最少使用的图片从缓存中移除。

2.当前GridView是静止的，则调用loadBitmaps()方法去下载图片，如果GridView正在滚动，则取消掉所有下载任务，这样可以保证GridView滚动的流畅性

3.所有可见的GridView子元素开启了一个线程去执行下载任务，下载成功后将图片存储到LruCache当中，然后通过Tag找到相应的ImageView控件，把下载好的图片显示出来。

4.设置了最大缓存容量为程序最大可用内存的1/8，接下来又为GridView注册了一个滚动监听器。然后在getView()方法中，我们为每个ImageView设置了一个唯一的Tag，这个Tag的作用是为了后面能够准确地找回这个ImageView，不然异步加载图片会出现乱序的情况。之后调用了setImageView()方法为ImageView设置一张图片，这个方法首先会从LruCache缓存中查找是否已经缓存了这张图片，如果成功找到则将缓存中的图片显示在ImageView上，否则就显示一张默认的空图片

5.程序中没有使用本地缓存，所有被释放掉的图片再次显示需要从网络上再下载一遍。在实际的项目中配合适当的本地缓存效果会更好
