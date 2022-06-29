import logo from './logo.svg';
import './App.less';
import {Button, ConfigProvider, DatePicker, message} from "antd";
import React, { useState } from 'react';
import zhCN from 'antd/lib/locale/zh_CN';
import 'moment/locale/zh-cn';

function App() {
  const [date, setDate] = useState(null);
  const handleChange = value => {
    message.info(`您选择的日期是: ${value ? value.format('YYYY年MM月DD日') : '未选择'}`);
    setDate(value);
  };
  return (
    <ConfigProvider locale={zhCN}>
      <div style={{ width: 400, margin: '100px auto' }}>
        <DatePicker onChange={handleChange} />
        <div style={{ marginTop: 16 }}>
          当前日期：{date ? date.format('YYYY年MM月DD日') : '未选择'}
        </div>
        <Button type="primary">Button</Button>
      </div>
    </ConfigProvider>
  );
}

export default App;
