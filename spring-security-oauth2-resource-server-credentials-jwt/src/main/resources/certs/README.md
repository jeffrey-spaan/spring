# How To Create Certificates

## 1. Prerequisites
- [Install Git Bash](https://git-scm.com/downloads)

## 2. Create keypair.pem
- Open Git Bash
- Type the following:
```bash
 openssl genrsa -out keypair.pem
```

## 3. Create public.pem
```bash
 openssl rsa -in keypair.pem -pubout -out public.pem
```

## 4. Create private.pem
```bash
 openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

## 5. Delete keypair.pem
Remove the keypair.pem from your directory.