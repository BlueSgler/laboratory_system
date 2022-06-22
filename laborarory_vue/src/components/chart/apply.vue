<template>
  <div class="box1">
    
    <div
      id="applyPrice"
      :style="{ width: '590px', height: '600px' }"
      v-if="isShowChart"
    ></div>
    <h4>总计：{{ total }}人民币</h4>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import api from '@/api/index'
import { onMounted, onUnmounted, reactive, ref } from 'vue'
export default {
  setup() {
    let isShowChart = ref(true)
    const total = ref(0)
    onMounted(async () => {

      let myChart = echarts.init(document.getElementById('applyPrice'));
      let data1 = ref(
        [
          {
            name: '谷类',
            value: 175.17,
          },
          {
            name: '豆类',
            value: 148.35,
          },
          {
            name: '油料作物',
            value: 95.36,
          },
          {
            name: '中草药材',
            value: 56,
          },
          {
            name: '蔬菜及食用菌',
            value: 45,
          },
          {
            name: '瓜果类',
            value: 34,
          },
          {
            name: '其他作物',
            value: 95.36,
          },
        ]
      )
      let xAxisData = ref([])
      let seriesData1 = ref([])
      let sum = ref(0);
      let barTopColor = ['#02c3f1', '#53e568', '#02c3f1', '#53e568', '#02c3f1', '#53e568', '#02c3f1', '#53e568',];
      let barBottomColor = [
        'rgba(2,195,241,0.1)',
        'rgba(83, 229, 104, 0.1)',

        'rgba(2,195,241,0.1)',
        'rgba(83, 229, 104, 0.1)',

        'rgba(2,195,241,0.1)',
        'rgba(83, 229, 104, 0.1)',

        'rgba(2,195,241,0.1)',
        'rgba(83, 229, 104, 0.1)',
      ];
      const { data: res } = await api.getApply()
      console.log(res);
      data1.value = res.data
      //   total.value = 123

      for (let i = 0; i < res.data.length; i++) {
        total.value = res.data[i].value + total.value
      }
      data1.value.forEach((item) => {
        xAxisData.value.push(item.name);
        seriesData1.value.push(item.value);
        sum.value += item.value;
      });
      const option = reactive(
        {
          //   backgroundColor: '#000',
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
          grid: {
            top: '20%',
            bottom: '100',
            left: '50',
            right: '100',
            containLabel: false,
          },
          xAxis: {
            data: xAxisData,
            axisTick: {
              show: false,
            },
            axisLine: {
              show: false,
            },
            axisLabel: {
              show: true,
              margin: 25,
              rotate: 25,
              align: 'center',
              textStyle: {
                fontSize: 12,
                color: '#ffffff',
              },
            },
            interval: 0,
          },
          yAxis: {
            type: 'value',
            // name: '件',
            nameTextStyle: {
              color: '#FFFFFF',
              fontSize: 12,
            },
            splitLine: {
              show: false,
            },
            axisTick: {
              show: false,
            },
            axisLine: {
              show: false,
            },
            axisLabel: {
              show: false,
            },
          },
          series: [
            {
              name: '柱顶部',
              type: 'pictorialBar',
              symbolSize: [26, 10],
              symbolOffset: [0, -5],
              z: 12,
              itemStyle: {
                // normal: {
                color: function (params) {
                  return barTopColor[params.dataIndex];
                },
                // },
              },
              label: {
                show: false,
                position: 'top',
                fontSize: 16,
              },
              symbolPosition: 'end',
              data: seriesData1,
            },
            {
              name: '柱底部',
              type: 'pictorialBar',
              symbolSize: [26, 10],
              symbolOffset: [0, 5],
              z: 12,
              itemStyle: {
                // normal: {
                color: function (params) {
                  return barTopColor[params.dataIndex];
                },
                // },
              },
              data: seriesData1,
            },
            {
              type: 'bar',
              itemStyle: {
                // normal: {
                color: function (params) {
                  return new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    {
                      offset: 1,
                      color: barTopColor[params.dataIndex],
                    },
                    {
                      offset: 0,
                      color: barBottomColor[params.dataIndex],
                    },
                  ]);
                },
                opacity: 0.8,
                // },
              },
              z: 16,
              silent: true,
              barWidth: 26,
              barGap: '-100%', // Make series be overlap
              data: seriesData1,
            },
          ],
        }

      )



      myChart.setOption(option)
    })

    onUnmounted(() => {
      isShowChart.value = false
    })
    return {
      isShowChart,
      total
    }
  }
}
</script>

<style lang="less" scoped>
</style>