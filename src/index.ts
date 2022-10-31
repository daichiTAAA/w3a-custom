import { registerPlugin } from '@capacitor/core';

import type { W3aCustomPlugin } from './definitions';

const W3aCustom = registerPlugin<W3aCustomPlugin>('W3aCustom', {
  web: () => import('./web').then(m => new m.W3aCustomWeb()),
});

export * from './definitions';
export { W3aCustom };
