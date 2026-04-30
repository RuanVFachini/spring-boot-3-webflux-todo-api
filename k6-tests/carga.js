import http from 'k6/http';
import { check, group, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 10000 },   // Ramp-up: até 20 usuários
    { duration: '1m', target: 5000 },    // Load: até 500 usuários
    { duration: '30s', target: 0 },    // Ramp-down
  ],
  thresholds: {
    'http_req_duration': ['p(95)<2000'],   // 95% deve ser < 2s
    'http_req_failed': ['rate<0.1'],       // Taxa de erro < 10%
    'group_duration{group:::TodoCreation}': ['p(95)<3000'], // Fluxo completo < 3s
  },
};
const BASE_URL = 'http://host.docker.internal:8080';
const TOKEN = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZTEiLCJpc3MiOiJ0ZXN0ZSIsImV4cCI6MTc3NzU4NjAxOH0.W7Dfect8QKuTeQL580uHjpQJ63gy93NKE501w26Ak3s'

export default function () {
  
  group('TodoCreation', function () {
    const todo = http.post(`${BASE_URL}/api/benchmark`, JSON.stringify({
      description: "item 1"
    }), {
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + TOKEN
      },
    });
    check(todo, {
      'todo criado': (r) => r.status === 200,
      'latência < 3000ms': (r) => r.timings.duration < 3000,
    });
    sleep(1);
  });
}