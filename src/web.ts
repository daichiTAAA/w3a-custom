import { WebPlugin } from '@capacitor/core';

import type { W3aCustomPlugin } from './definitions';

export class W3aCustomWeb extends WebPlugin implements W3aCustomPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async login(): Promise<void> {
    console.log('LOGIN');
  }

  async logout(): Promise<void> {
    console.log('LOGOUT');
  }

  async getPrivateKey(): Promise<{ value: string }> {
    return { value: 'Login' };
  }
}
