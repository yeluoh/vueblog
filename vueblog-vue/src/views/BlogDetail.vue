<template>
  <div>
    <Header></Header>
    <div class="Detblog">
      <h2>{{blog.title}}</h2>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content">

      </div>

      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link :to="{name: 'BlogEdit',params: {blogId: blog.id}}">
          编辑
        </router-link>
      </el-link>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import 'github-markdown-css/github-markdown.css'
export default {
  name: "BlogDetail.vue",
  components: {Header},
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: ""
      },
      ownBlog: false
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    const _this = this
    console.log(blogId)
    this.$axios.get('/blog/' + blogId).then(res => {
      const blog = res.data.data
      _this.blog.id = blog.id
      _this.blog.title = blog.title

      var MardownIt = require("markdown-it")
      var md = new MardownIt()
      var result = md.render(blog.content)
      _this.blog.content = result

      _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
    })
  }
}
</script>

<style scoped>

  .Detblog{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    min-height: 700px;
    padding: 20px 15px;

  }

</style>