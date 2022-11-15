export interface W3aCustomPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  login(): Promise<void>;
  logout(): Promise<void>;
  getPrivateKey(): Promise<{ value: string }>;
}
