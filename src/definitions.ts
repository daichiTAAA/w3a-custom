export interface W3aCustomPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
