# w3a-custom

Web3Auth custom plugin for capacitor

## Install

```bash
npm install w3a-custom
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`login()`](#login)
* [`logout()`](#logout)
* [`getPrivateKey()`](#getprivatekey)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### login()

```typescript
login() => Promise<void>
```

--------------------


### logout()

```typescript
logout() => Promise<void>
```

--------------------


### getPrivateKey()

```typescript
getPrivateKey() => Promise<{ value: string; }>
```

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------

</docgen-api>
