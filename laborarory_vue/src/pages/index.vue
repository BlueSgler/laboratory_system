<template>
  <div class="box">
    <n-card>
      <!-- 面包屑导航栏区域 -->
      <n-breadcrumb separator=">">
        <n-breadcrumb-item> 主页 </n-breadcrumb-item>
      </n-breadcrumb>
      <h1>设备统计</h1>

      <div class="main-box">
        <div class="box1">
          <h2>设备类别统计</h2>
          <div
            id="myChart"
            :style="{ width: '590px', height: '600px' }"
            v-if="isShowChart"
          ></div>
        </div>
        <div class="box2">
          <h2>设备状态统计</h2>
          <div
            id="main2"
            style="width: 590px; height: 600px"
            v-if="isShowChart"
          ></div>
        </div>
      </div>
      <h1>资金统计</h1>
      <h2>申请资金统计</h2>
      <div class="main-box">
        <ApplyPrice />
      </div>
      <h2>修理资金统计</h2>
      <div class="main-box">
        <RepairPrice />
      </div>
    </n-card>
  </div>
</template>

<script>

import * as echarts from 'echarts'
import api from '@/api/index'
import { onMounted, reactive, ref, onUnmounted } from '@vue/runtime-core';
import ApplyPrice from '@/components/chart/apply.vue'
import RepairPrice from '@/components/chart/repair.vue'

export default {
  components: {
    ApplyPrice,
    RepairPrice
  },
  name: "data_page",
  setup() {
    let isShowChart = ref(true)
    // onMounted
    onMounted(async () => {//需要获取到element,所以是onMounted的Hook
      let myChart = echarts.init(document.getElementById("myChart"));
      let myChart2 = echarts.init(document.getElementById('main2'));


      //设备类别统计
      let option = reactive({
        tooltip: {
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ['pie', 'funnel'],
              option: {
                funnel: {
                  x: '25%',
                  width: '50%',
                  funnelAlign: 'left',
                  max: 1548
                }
              }
            },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        label: {
          formatter: '{a|{c}}{l|个}\n{per|{b}}\n{sq|一}{per|占比}{b|{d}%}',
          rich: {
            l: { align: 'left', color: '#A5CDF2' },
            a: {
              color: 'inherit',
              fontSize: 21,
              align: 'left',
            },
            b: {
              color: 'inherit',
              fontSize: 16,
              lineHeight: 22,
              align: 'left',
            },
            per: {
              color: '#fff',
              padding: [3, 4],
              borderRadius: 4,
              align: 'left',
            },
            sq: {
              color: 'inherit',
              backgroundColor: 'inherit',
            },
          },
        },

        series: [
          {
            name: '情况统计',
            type: 'pie',
            selectedMode: 'single',
            radius: ['50%', '60%'],
            label: {
              show: false,
            },
            data: [
              {
                value: 67,
                name: '已建成投运',
                itemStyle: {
                  color: 'rgba(102, 204, 255, .6)',
                },
              },
              {
                value: 10,
                name: '建设中',
                itemStyle: {
                  color: 'rgba(51, 255, 187, .6)',
                },
              },
              {
                value: 6,
                name: '未建设',
                itemStyle: {
                  color: 'rgba(255, 236, 140, .6)',
                },
              },
            ],
          },
          {
            name: '情况统计',
            type: 'pie',
            radius: ['59%', '75%'],
            data: [
              {
                value: 33,
                name: '已建投运',
                itemStyle: {
                  color: 'rgba(102, 204, 255, 1)',
                },
              },
              {
                value: 10,
                name: '建设中',
                itemStyle: {
                  color: 'rgba(51, 255, 187, 1)',
                },
              },
              {
                value: 6,
                name: '未建设',
                itemStyle: {
                  color: 'rgba(255, 236, 140, 1)',
                },
              },
            ],
          },

          {
            type: 'gauge',
            center: ['50%', '50%'],
            radius: '45%',
            startAngle: 0,
            endAngle: 360,

            splitLine: {
              show: false,
            },
            axisTick: {
              show: false,
            },
            axisLabel: {
              show: false,
              distance: 50,
            },
            axisLine: {
              lineStyle: {
                width: 2,
                color: [[1, 'rgba(6, 191, 255, 1)']],
              },
            },
            pointer: {
              show: false,
            },
            detail: {
              valueAnimation: true,
              width: '100%',
              lineHeight: 40,
              offsetCenter: [0, 0],
              fontSize: 34,
              fontWeight: 'normal',
              color: 'rgba(102, 255, 255, 1)',
              formatter: '{b|设备总数}\n{value}{a|个}',
              rich: {
                a: {
                  fontSize: 18,
                },
                b: {
                  fontSize: 15,
                  color: '#fff',
                },
              },
            },
            data: [
              {
                value: 89,
              },
            ],
          },
        ],
      })
      const { data: res } = await api.getEquipment('category')

      for (let i = 0; i < 3; i++) {
        option.series[0].data[i].name = res.data[i].name
        option.series[0].data[i].value = res.data[i].value
        option.series[1].data[i].name = res.data[i].name
        option.series[1].data[i].value = res.data[i].value
      }
      //总数
      option.series[2].data[0].value = res.data[0].value + res.data[1].value + res.data[2].value


      //设备状态部分
      let color = ['#0E7CE2', '#FF8352', '#E271DE', '#F8456B', '#00FFFF', '#4AEAB0'];
      let echartData = ref([
        {
          name: '设备1',
          value: '3720',
        },
        {
          name: '设备2',
          value: '2920',
        },
        // {
        //   name: '设备3',
        //   value: '2200',
        // },
      ])
      let formatNumber = function (num) {
        let reg = /(?=(\B)(\d{3})+$)/g;
        return num.toString().replace(reg, ',');
      };
      let option2 = reactive({
        // backgroundColor: '#364686',
        tooltip: {
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: {
              show: true,
              type: ['pie', 'funnel'],
              option: {
                funnel: {
                  x: '25%',
                  width: '50%',
                  funnelAlign: 'left',
                  max: 1548
                }
              }
            },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        color: color,
        title: [
          {
            text: '状态',
            x: 'center',
            top: '42%',
            textStyle: {
              color: '#fff',
              fontSize: 34,
              fontWeight: '100',
            },
          },
          {
            text: '71%',
            x: 'center',
            top: '50%',
            textStyle: {
              fontSize: 76,
              color: '#00f0ff',
              foontWeight: '500',
            },
          },
        ],
        polar: {
          radius: ['44%', '50%'],
          center: ['50%', '50%'],
        },
        angleAxis: {
          max: 100,
          show: false,
        },
        radiusAxis: {
          type: 'category',
          show: true,
          axisLabel: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          axisTick: {
            show: false,
          },
        },
        series: [
          {
            type: 'pie',
            radius: ['55%', '67%'],
            center: ['50%', '50%'],
            data: echartData,
            emphasis: {
              scale: false
            },
            itemStyle: {
              // normal: {
              borderColor: '#364684',
              borderWidth: 2,
              // },
            },
            labelLine: {
              // normal: {
              length: 60,
              length2: 60,
              lineStyle: {
                color: '#e6e6e6',
              },
              // },
            },
            label: {
              // normal: {
              formatter: (params) => {
                return '{icon|●}{name|' + params.name + '}\n{value|' + formatNumber(params.value) + '}';
              },
              padding: [0, -70, 30, -70],
              rich: {
                icon: {
                  fontSize: 16,
                  align: 'left',
                  padding: [4, 0, 0, 0],
                },
                name: {
                  fontSize: 14,
                  align: 'left',
                  padding: [4, 0, 0, 0],
                  color: '#fff',
                },
                value: {
                  fontSize: 18,
                  fontWeight: 'bold',
                  color: '#fff',
                  align: 'left',
                },
              },
              // },
            },
          },
          {
            name: '',
            type: 'pie',
            startAngle: 90,
            radius: '50%',
            emphasis: {
              scale: false
            },
            center: ['50%', '50%'],
            itemStyle: {
              // normal: {
              labelLine: {
                show: false,
              },
              color: new echarts.graphic.RadialGradient(0.5, 0.5, 1, [
                {
                  offset: 1,
                  color: 'rgba(50,171,241, 1)',
                },
                {
                  offset: 0,
                  color: 'rgba(55,70,130, 0)',
                },
              ]),
              // borderWidth: 1,
              // borderColor: '',
              shadowBlur: 10,
              // shadowColor: 'rgba(55,70,130, 1)'
              // },
            },
            data: [
              {
                value: 100,
              },
            ],
          },
        ],
      });

      const { data: res2 } = await api.getEquipment('status')
      console.log(res2.data);
      echartData.value = res2.data
      // 绘制图表
      myChart.setOption(option);
      myChart2.setOption(option2)

      // window.onresize = function () {//自适应大小
      //   myChart.resize();
      // };
    });
    onUnmounted(() => {
      isShowChart.value = false
    })

    return {
      isShowChart
    }
  },


}
</script>

<style lang="less" scoped>
.box {
  width: 100%;
  height: 100%;
  background-color: #fff;
  .n-card {
    margin: 30px auto;
    width: 95%;
    // height: 95%;
  }
  .main-box {
    width: 100%;
    display: flex;
    justify-content: center;
  }
}
</style>